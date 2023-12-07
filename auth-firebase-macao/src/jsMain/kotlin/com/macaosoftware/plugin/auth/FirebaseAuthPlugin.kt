package com.macaosoftware.plugin.auth

import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginError
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.LoginRequestForEmailWithLink
import com.macaosoftware.plugin.LoginRequestForLink
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.ProviderData
import com.macaosoftware.plugin.SignupError
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.UserData
import com.macaosoftware.plugin.util.MacaoError
import com.macaosoftware.plugin.util.MacaoErrors
import com.macaosoftware.plugin.util.MacaoResult
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.ActionCodeSettings
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.UserInfo
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.database.database

class FirebaseAuthPlugin : AuthPlugin {
    private val firebaseAuth = Firebase.auth
    private val firebaseDatabase = Firebase.database.reference().child("Users")

    override suspend fun initialize(): Boolean {
        return true
    }

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(
                signupRequest.email,
                signupRequest.password
            )

            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                storeUserData(
                    firebaseUser.uid,
                    signupRequest.email,
                    signupRequest.username,
                    signupRequest.password,
                    "https://macao-software.com/",
                    "United States"
                )

                // Map the FirebaseUser to your MacaoUser model
                val macaoUser = mapToMacaoUser(firebaseUser, signupRequest)

                // Return the MacaoResult with Success
                MacaoResult.Success(MacaoUser(macaoUser.email!!))
            } else {
                // Sign up failed
                MacaoResult.Error(SignupError(errorDescription = "Empty User"))
            }
        } catch (e: Exception) {
            // Return the MacaoResult with Error
            MacaoResult.Error(SignupError(errorDescription = e.message.toString()))
        }
    }


    private suspend fun storeUserData(
        userId: String,
        email: String,
        displayName: String,
        password: String,
        photoUrl: String,
        country: String
    ) {
        // Create a map with user data
        val userData = mapOf(
            "uid" to userId,
            "email" to email,
            "displayName" to displayName,
            "password" to password,
            "photoUrl" to photoUrl,
            "country" to country
            // Add more fields as needed
        )

        // Store data in the "Users" node with the user's ID as the key
        val usersReference = Firebase.database.reference().child("Users")
        usersReference.child(userId).setValue(userData)
    }

    private fun mapToMacaoUser(firebaseUser: FirebaseUser, signupRequest: SignupRequest): UserData {
        return UserData(
            uid = firebaseUser.uid,
            email = signupRequest.email,
            displayName = signupRequest.username,
            password = signupRequest.password,
            photoUrl = "https://macao-software.com/",
            country = "United States"
            // Add more fields as needed
        )
    }


    override suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser> {
        return try {
            if (isValidEmail(loginRequest.email) && isValidPassword(loginRequest.password)) {
                val userExists = userExistsInDatabase(loginRequest.email, loginRequest.password)

                if (userExists) {
                    val authResult = firebaseAuth.signInWithEmailAndPassword(
                        loginRequest.email,
                        loginRequest.password
                    )
                    MacaoResult.Success(MacaoUser(loginRequest.email))
                } else {
                    // User does not exist
                    MacaoResult.Error(LoginError(errorDescription = "Invalid email or password"))
                }
            } else {
                // Invalid email or password format
                MacaoResult.Error(LoginError(errorDescription = "Invalid email or password format"))
            }
        } catch (e: Exception) {
            // Return the MacaoResult with Error
            MacaoResult.Error(LoginError(errorDescription = "Login Failed: ${e.message}"))
        }
    }


    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        try {
            val auth = Firebase.auth
            if (auth.isSignInWithEmailLink(loginRequest.link)) {
                val email = loginRequest.email
                auth.signInWithEmailLink(email, loginRequest.link)
            }
        } catch (e: Exception) {
            MacaoResult.Error(MacaoErrors.CustomError(e.printStackTrace().toString()))
        }
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        try {
            val email = loginRequest.email
            val actionCodeSettings = ActionCodeSettings(
                url = "https://macao-sdui-app-30-default-rtdb.firebaseio.com/",
                canHandleCodeInApp = false,
            )
            firebaseAuth.sendSignInLinkToEmail(email, actionCodeSettings)
        } catch (e: Exception) {
            // Handle exceptions
            MacaoResult.Error(MacaoErrors.CustomError(e.printStackTrace().toString()))
        }
    }


    override suspend fun checkCurrentUser(): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            if (user != null) {
                MacaoResult.Success(MacaoUser(user.email!!))
            } else {
                MacaoResult.Error(MacaoErrors.CustomError(errorDescription = "No User Found...."))
            }
        } catch (e: Exception) {
            MacaoResult.Error(MacaoErrors.CustomError(errorDescription = "Error checking current user: ${e.message}"))

        }
    }

    override suspend fun getUserProfile(): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Get user profile details
                val name = it.displayName
                val email = it.email
                val photoUrl = it.photoURL
                val macaoUser = mapToMacaoUser(
                    user,
                    signupRequest = SignupRequest(
                        name!!,
                        email!!,
                        it.displayName!!,
                        it.phoneNumber!!
                    )
                )
                MacaoResult.Success(MacaoUser(macaoUser.email!!))
            } ?: run {
                // No user is signed in
                MacaoResult.Error(MacaoErrors.CustomError(errorDescription = "No User Found..."))
            }
        } catch (e: Exception) {
            // Return the MacaoResult with Error
            MacaoResult.Error(MacaoErrors.CustomError(errorDescription = "Error getting user profile: ${e.message}"))
        }
    }

    override suspend fun getProviderData(): MacaoResult<ProviderData> {
        return try {
            val user = firebaseAuth.currentUser
            val providerData = user?.providerData?.firstOrNull()
            val providerDataModel = mapToProviderData(providerData)
            MacaoResult.Success(providerDataModel)
        } catch (e: Exception) {
            // Return the MacaoResult with Error
            MacaoResult.Error(MacaoErrors.CustomError("Error getting provider data: ${e.message}"))
        }
    }

    private fun mapToProviderData(providerData: UserInfo?): ProviderData {
        // Map the provider data to your ProviderData model
        return ProviderData(
            email = providerData?.email,
            displayName = providerData?.displayName,
            phoneNumber = providerData?.phoneNumber,
            photoUrl = providerData?.photoURL
        )
    }


    override suspend fun updateProfile(
        displayName: String,
        photoUrl: String
    ): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                val macaoUser = UserData(displayName = displayName, photoUrl = photoUrl)
                firebaseDatabase.child(it.uid).setValue(macaoUser)
                MacaoResult.Success(MacaoUser(it.email ?: ""))
            } ?: run {
                MacaoResult.Error(MacaoErrors.CustomError("No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(MacaoErrors.CustomError("Error updating user profile: ${e.message}"))
        }
    }


    override suspend fun updateFullProfile(
        displayName: String?,
        country: String?,
        photoUrl: String?,
        phoneNo: String?,
        facebookLink: String?,
        linkedIn: String?,
        github: String?
    ): MacaoResult<UserData> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Create a map with user data to update
                val userData = mapOf(
                    "displayName" to displayName,
                    "country" to country,
                    "photoUrl" to photoUrl,
                    "phoneNo" to phoneNo,
                    "facebookLink" to facebookLink,
                    "linkedIn" to linkedIn,
                    "github" to github
                    // Add more fields as needed
                )

                // Update user profile in the database
                firebaseDatabase.child("Users").child(it.uid).updateChildren(userData)

                MacaoResult.Success(UserData(userData.toString()))

            } ?: run {
                MacaoResult.Error(MacaoErrors.CustomError("No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(MacaoErrors.CustomError("Error updating user profile: ${e.message}"))
        }
    }


    override suspend fun updateEmail(newEmail: String): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Update user email
                it.updateEmail(newEmail)
                MacaoResult.Success(MacaoUser(newEmail))
            } ?: run {
                MacaoResult.Error(MacaoErrors.CustomError("No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(MacaoErrors.CustomError("Error updating user email: ${e.message}"))
        }
    }


    override suspend fun updatePassword(newPassword: String): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Update user password
                it.updatePassword(newPassword)
                MacaoResult.Success(MacaoUser(it.email ?: ""))
            } ?: run {
                MacaoResult.Error(MacaoErrors.CustomError("No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(MacaoErrors.CustomError("Error updating user password: ${e.message}"))
        }
    }


    override suspend fun sendEmailVerification(): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                it.sendEmailVerification()
                MacaoResult.Success(MacaoUser(it.email ?: ""))
            } ?: run {
                MacaoResult.Error(MacaoErrors.CustomError("No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(MacaoErrors.CustomError("Error sending email verification: ${e.message}"))
        }
    }


    override suspend fun sendPasswordReset(): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Send password reset email
                firebaseAuth.sendPasswordResetEmail(user.email!!)
                MacaoResult.Success(MacaoUser(user.email ?: ""))
            } ?: run {
                MacaoResult.Error(MacaoErrors.CustomError("No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(MacaoErrors.CustomError("Error sending password reset email: ${e.message}"))
        }
    }


    override suspend fun deleteUser(): MacaoResult<Unit> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Delete user account
                it.delete()
                MacaoResult.Success(Unit)
            } ?: run {
                // Return error if no user is signed in
                MacaoResult.Error(MacaoErrors.CustomError("No user is signed in"))
            }
        } catch (e: Exception) {
            // Return error with the exception message
            MacaoResult.Error(MacaoErrors.CustomError("Error deleting user account: ${e.message}"))
        }
    }

    override suspend fun fetchUserData(): MacaoResult<UserData> {
        return try {
            val userId = firebaseAuth.currentUser?.uid

            userId?.let { uid ->
                val userReference = Firebase.database.reference().child("Users").child(uid)
                val userDataFlow = userReference.valueEvents

                var userData: UserData? = null

                userDataFlow.collect { dataSnapshot ->
                    val displayName = dataSnapshot.child("displayName").value?.toString()
                    val country = dataSnapshot.child("country").value?.toString()

                    userData = UserData(displayName, country /*, other properties */)
                }

                userData?.let {
                    MacaoResult.Success(it)
                } ?: run {
                    MacaoResult.Error(LoginError(errorDescription = "User data not found"))
                }
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error fetching user data: ${e.message}"))
        }
    }

    override suspend fun checkAndFetchUserData(): MacaoResult<UserData> {
        val currentUser = firebaseAuth.currentUser

        return if (currentUser != null) {
            try {
                fetchUserData()
            } catch (e: Exception) {
                MacaoResult.Error(LoginError(errorDescription = "Error fetching user data: ${e.message}"))
            }
        } else {
            MacaoResult.Error(LoginError(errorDescription = "User is not signed in"))
        }
    }

    override suspend fun logoutUser(): MacaoResult<Unit> {
        return try {
            firebaseAuth.signOut()
            MacaoResult.Success(Unit)
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error logging out: ${e.message}"))
        }
    }


    private fun userExistsInDatabase(email: String, password: String): Boolean {
        return firebaseAuth.currentUser!!.equals(email) && firebaseAuth.currentUser!!.isEmailVerified
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return email.trim().matches(emailRegex)
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }
}