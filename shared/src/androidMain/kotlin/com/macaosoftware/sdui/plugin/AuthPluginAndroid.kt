package com.macaosoftware.sdui.plugin

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Build
import android.service.autofill.UserData
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginError
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.LoginRequestForEmailWithLink
import com.macaosoftware.plugin.LoginRequestForLink
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.ProviderData
import com.macaosoftware.plugin.SignupError
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.util.MacaoResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthPluginAndroid : AuthPlugin {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersReference: DatabaseReference = database.reference.child("users")


    override suspend fun initialize(): Boolean {
        // Initialization logic (if needed)
        return true
    }
    override suspend fun fetchUserData(): MacaoResult<com.macaosoftware.plugin.UserData> {
        return try {
            val usersReference = FirebaseDatabase.getInstance().reference.child("Users")
            val userReference = usersReference.child(firebaseAuth.currentUser!!.uid)
            val dataSnapshot = userReference.get().await()
            val userListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userData = snapshot.getValue<com.macaosoftware.plugin.UserData>()
                    println(" User Listener ${userData.toString()}")
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            userReference.addValueEventListener(userListener)
            val userData = dataSnapshot.getValue(com.macaosoftware.plugin.UserData::class.java)

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

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(
                signupRequest.email,
                signupRequest.password
            )

            result.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = firebaseAuth.currentUser
                    user?.let {
                        // Store user data in the database after successful sign-up
                        storeUserData(
                            it.uid,
                            signupRequest.email,
                            signupRequest.username,
                            signupRequest.password,
                            "https://macao-software.com/",
                            "United States"
                        )
                    }                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    // updateUI(null)
                }
            }

            result.await()

            result.result?.user?.let {
                // Sign up success
                MacaoResult.Success(MacaoUser(signupRequest.email))
            } ?: run {
                // Sign up failed
                MacaoResult.Error(SignupError(errorDescription = "Empty User"))
            }
        } catch (e: Exception) {
            Log.e(
                "com.macaosoftware.sdui.plugin.AuthPluginAndroid",
                "Signup Failed: ${e.message}",
                e
            )
            MacaoResult.Error(SignupError(errorDescription = e.message ?: "Unknown error"))
        }
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

    override suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser> {
        return try {
            if (isValidEmail(loginRequest.email) && isValidPassword(loginRequest.password)) {
                val result = firebaseAuth.signInWithEmailAndPassword(
                    loginRequest.email,
                    loginRequest.password
                ).await()

                result.user?.let {
                    // Login success
                    MacaoResult.Success(MacaoUser(loginRequest.email))
                } ?: run {
                    // Login failed
                    MacaoResult.Error(LoginError(errorDescription = "Empty User"))
                }
            } else {
                MacaoResult.Error(
                    LoginError(
                        errorDescription = "Invalid email or password format"
                    )
                )
            }
        } catch (e: FirebaseAuthException) {
            Log.e(
                "com.macaosoftware.sdui.plugin.AuthPluginAndroid",
                "Login Failed: ${e.message}",
                e
            )
            MacaoResult.Error(LoginError(errorDescription = "Login Failed: ${e.message}"))
        }
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        // Implement if needed
        // Note: Firebase on Android usually handles email link login automatically
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        // Implement if needed
        // Note: Firebase on Android usually handles email link sending automatically
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
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return email.trim().matches(emailRegex)
    }

    private fun isValidPassword(password: String): Boolean {
        // Implement password validation logic if needed
        return password.length >= 8
    }
}
