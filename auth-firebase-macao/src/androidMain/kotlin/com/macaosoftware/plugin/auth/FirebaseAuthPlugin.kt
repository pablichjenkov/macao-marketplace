package com.macaosoftware.plugin.auth

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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
import com.macaosoftware.plugin.util.MacaoResult
import kotlinx.coroutines.tasks.await

class FirebaseAuthPlugin : AuthPlugin {

    private val TAG = "FirebaseAuthPlugin"
    private val firebaseAuth: FirebaseAuth = Firebase.auth
    private val firebaseDatastore: FirebaseDatabase = Firebase.database

    override suspend fun initialize(): Boolean {
        return true
    }

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {

        return try {
            val taskResult = firebaseAuth
                .createUserWithEmailAndPassword(signupRequest.email, signupRequest.password)
                .await()
            taskResult.user?.let {
                storeUserData(
                    it.uid,
                    signupRequest.email,
                    signupRequest.username,
                    signupRequest.password,
                    "https://macao-software.com/",
                    "United States"
                )
                MacaoResult.Success(it.toMacaoUser())
            } ?: run {
                MacaoResult.Error(SignupError(errorDescription = "createUserWithEmailAndPassword:success but unexpected use null"))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            MacaoResult.Error(SignupError(errorDescription = ex.message.orEmpty()))
        }
    }

    override suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser> {

        if (!isValidEmail(loginRequest.email) || !isValidPassword(loginRequest.password)) {
            return MacaoResult.Error(
                LoginError(
                    errorDescription = "Invalid email or password format"
                )
            )
        }
        return try {
            val taskResult = firebaseAuth
                .signInWithEmailAndPassword(loginRequest.email, loginRequest.password)
                .await()

            return taskResult.user?.let {
                MacaoResult.Success(it.toMacaoUser())
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "signInWithEmailAndPassword:success but unexpected use null"))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            MacaoResult.Error(LoginError(errorDescription = ex.message.orEmpty()))
        }
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        // TODO("Not yet implemented")
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        // TODO("Not yet implemented")
    }

    override suspend fun checkCurrentUser(): MacaoResult<MacaoUser> {

        return try {
            val user = firebaseAuth.currentUser
            if (user != null) {
                MacaoResult.Success(MacaoUser(user.email ?: ""))
            } else {
                MacaoResult.Error(LoginError(errorDescription = "No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error checking current user: ${e.message}"))
        }
    }

    override suspend fun getUserProfile(): MacaoResult<MacaoUser> {

        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Get user profile details
                val name = it.displayName
                val email = it.email
                val photoUrl = it.photoUrl

                MacaoResult.Success(MacaoUser(email ?: ""))
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error getting user profile: ${e.message}"))
        }
    }

    override suspend fun getProviderData(): MacaoResult<ProviderData> {
        return try {
            val user = firebaseAuth.currentUser
            val providerData = user?.providerData?.firstOrNull()
            val providerDataModel = ProviderData(
                email = providerData?.email,
                displayName = providerData?.displayName,
                phoneNumber = providerData?.phoneNumber,
                photoUrl = providerData?.photoUrl?.toString(),
                // Extract additional fields as needed
            )
            MacaoResult.Success(providerDataModel)
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error getting provider data: ${e.message}"))
        }
    }

    override suspend fun updateProfile(
        displayName: String,
        photoUrl: String
    ): MacaoResult<MacaoUser> {

        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Update user profile
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(Uri.parse(photoUrl))
                    .build()

                it.updateProfile(profileUpdates).await()

                MacaoResult.Success(MacaoUser(it.email ?: ""))
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error updating user profile: ${e.message}"))
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

        try {
            val userData = UserData(
                displayName = displayName,
                country = country,
                photoUrl = photoUrl,
                phoneNo = phoneNo,
                facebookLink = facebookLink,
                linkedIn = linkedIn,
                github = github
            )

            // Reference to the specific user node
            val userReference =
                firebaseDatastore.reference.child("Users").child(firebaseAuth.currentUser!!.uid)

            // Update data in the database
            userReference.updateChildren(userData.toMap())
                .addOnSuccessListener {
                    println("Data Updated Successfully")
                }
                .addOnFailureListener { e ->
                    println("Data Updation Failure: ${e.message}")
                }

            return MacaoResult.Success(userData)

        } catch (e: Exception) {
            return MacaoResult.Error(LoginError(errorDescription = "Error updating user data: ${e.message}"))
        }
    }

    override suspend fun updateEmail(newEmail: String): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Update user email
                it.updateEmail(newEmail).await()

                MacaoResult.Success(MacaoUser(newEmail))
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error updating user email: ${e.message}"))
        }
    }

    override suspend fun updatePassword(newPassword: String): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Update user password
                it.updatePassword(newPassword).await()

                MacaoResult.Success(MacaoUser(it.email ?: ""))
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error updating user password: ${e.message}"))
        }
    }

    override suspend fun sendEmailVerification(): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Send email verification
                it.sendEmailVerification().await()

                MacaoResult.Success(MacaoUser(it.email ?: ""))
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error sending email verification: ${e.message}"))
        }
    }

    override suspend fun sendPasswordReset(): MacaoResult<MacaoUser> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Send password reset email
                firebaseAuth.sendPasswordResetEmail(user.email!!).await()

                MacaoResult.Success(MacaoUser(user.email ?: ""))
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error sending password reset email: ${e.message}"))
        }
    }

    override suspend fun deleteUser(): MacaoResult<Unit> {
        return try {
            val user = firebaseAuth.currentUser
            user?.let {
                // Delete user account
                it.delete().await()

                MacaoResult.Success(Unit)
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "No user is signed in"))
            }
        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error deleting user account: ${e.message}"))
        }
    }

    override suspend fun fetchUserData(): MacaoResult<UserData> {

        return try {
            val usersReference = firebaseDatastore.reference.child("Users")
            val userReference = usersReference.child(firebaseAuth.currentUser!!.uid)
            val dataSnapshot = userReference.get().await()
            val userData = dataSnapshot.getValue(UserData::class.java)

            userReference.let {
                MacaoResult.Success(it)
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "User User Found"))
            }
            userData?.let {
                MacaoResult.Success(it)
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "User data not found"))
            }

        } catch (e: Exception) {
            MacaoResult.Error(LoginError(errorDescription = "Error fetching user data: ${e.message}"))
        }
    }

    override suspend fun checkAndFetchUserData(): MacaoResult<UserData> {
        // Check if user is signed in (non-null)
        val currentUser = firebaseAuth.currentUser
        return if (currentUser != null) {
            // User is already signed in, fetch user data
            try {
                fetchUserData()
            } catch (e: Exception) {
                // Handle exceptions if any
                MacaoResult.Error(LoginError(errorDescription = "Error fetching user data: ${e.message}"))
            }
        } else {
            // User is not signed in, return a placeholder or handle accordingly
            MacaoResult.Error(LoginError(errorDescription = "User is not signed in"))
        }
    }

    override suspend fun logoutUser(): MacaoResult<Unit> {
        return MacaoResult.Success(firebaseAuth.signOut())
    }

    private fun FirebaseUser.toMacaoUser(): MacaoUser {
        return MacaoUser(this.email ?: "no_email")
    }

    fun UserData.toMap(): Map<String, Any?> {
        return mapOf(
            "displayName" to displayName,
            "country" to country,
            "photoUrl" to photoUrl,
            "phoneNo" to phoneNo,
            "facebookLink" to facebookLink,
            "linkedIn" to linkedIn,
            "github" to github
        )
    }

    private fun storeUserData(
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

        // Store data in the "users" node with the user's ID as the key
        val usersReference = FirebaseDatabase.getInstance().reference.child("Users")
        usersReference.child(userId).setValue(userData)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return email.trim().matches(emailRegex)
    }

    private fun isValidPassword(password: String): Boolean {
        // Implement password validation logic if needed
        return password.length >= 8
    }
}
