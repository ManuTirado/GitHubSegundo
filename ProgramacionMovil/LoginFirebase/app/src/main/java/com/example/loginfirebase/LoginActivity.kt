package com.example.loginfirebase

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.*

class LoginActivity : AppCompatActivity() {
    companion object {
        var mGoogleSignInClient: GoogleSignInClient? = null
        val db = Firebase.firestore
    }

    enum class ProviderType {
        CORREO,
        GOOGLE
    }

    private val TAG = "LoginActivity"
    private val RC_SIGN_IN = 9001
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Analytics Event
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("msg", "Integración de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        // SetUp
        setUp()
    }

    //Google
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        Ingresado(currentUser)
    }


    private fun setUp() {
        title = "Autenticación"
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val logInButton = findViewById<Button>(R.id.logInButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        //Google
        val googleSignInBtn = findViewById<View>(R.id.btngmail)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        pedirUsuario(
                            emailEditText.context,
                            it.result.user?.email ?: "",
                            ProviderType.CORREO
                        )
                    } else {
                        showAlert()
                    }
                }
            }
        }

        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result.user?.email ?: "")
                    } else {
                        showAlert()
                    }
                }
            }
        }

        googleSignInBtn.setOnClickListener { ingreso() }
    }

    private fun pedirUsuario(cont: Context, email: String, provider: ProviderType) {
        var usu: String
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nombre de usuario")
        builder.setMessage("Ingrese el nombre de usuario para completar el registro")
        val input = EditText(cont)
        builder.setView(input)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
            usu = input.text.toString()
            db.collection("users").document(email).set(
                hashMapOf(
                    "provider" to provider,
                    "nombreUsuario" to usu
                )
            )
            Log.d("LoginActivity", "Usuario: " + usu)
            Log.d("LoginActivity", "email: " + email)
            showHome(email)
        })
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String) {
        HomeActivity.email = email
        val homeIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
    }


    //Google
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    //Google
    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = mAuth!!.currentUser
                    Ingresado(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(
                        TAG,
                        "signInWithCredential:failure",
                        task.exception
                    )
                    Ingresado(null)
                }
            }
    }


    //Google
    private fun ingreso() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    //Google
    private fun Ingresado(user: FirebaseUser?) {
        // [START check_current_user]
        val users = FirebaseAuth.getInstance().currentUser
        if (users != null) {
            // User is signed in
            showHome(user!!.email!!)
            Toast.makeText(this@LoginActivity, "Ingreso", Toast.LENGTH_SHORT).show()
        } else {
            // No user is signed in
        }
        // [END check_current_user]
    }
}