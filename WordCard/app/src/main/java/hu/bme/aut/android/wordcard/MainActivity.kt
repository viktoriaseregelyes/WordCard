package hu.bme.aut.android.wordcard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.android.wordcard.data.profile.Profile
import hu.bme.aut.android.wordcard.data.profile.ProfileDatabase
import hu.bme.aut.android.wordcard.databinding.ActivityMainBinding
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var database: ProfileDatabase
    private var foundSignIn: Boolean = false

    fun onEmailSelected(email: String?) {
        val showDetailsIntent = Intent()
        showDetailsIntent.setClass(this@MainActivity, CollectionActivity::class.java)
        showDetailsIntent.putExtra(CollectionActivity.EXTRA_PROF_EMAIL, email)
        startActivity(showDetailsIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = ProfileDatabase.getDatabase(applicationContext)

        onBackPressed()

        loadItemsInBackground()

        binding.btnLogin.setOnClickListener {
            if(binding.etEmailAddressLogin.text.toString().isEmpty()) {
                binding.etEmailAddressLogin.requestFocus()
                binding.etEmailAddressLogin.error = "Please enter your email address"
            }
            else if(binding.etPasswordLogin.text.toString().isEmpty()) {
                binding.etPasswordLogin.requestFocus()
                binding.etPasswordLogin.error = "Please enter your password"
            }
            else {
                getExistProfile(getProfileLogIn())
            }
        }

        binding.btnReg.setOnClickListener {
            if(binding.etEmailAddressReg.text.toString().isEmpty()) {
                binding.etEmailAddressReg.requestFocus()
                binding.etEmailAddressReg.error = "Please enter your email address"
            }
            else if(binding.etPasswordFirst.text.toString().isEmpty() || binding.etPasswordSecond.text.toString().isEmpty()) {
                if(binding.etPasswordFirst.text.toString().isEmpty()) {
                    binding.etPasswordFirst.requestFocus()
                    binding.etPasswordFirst.error = "Please enter your password"
                }
                if(binding.etPasswordSecond.text.toString().isEmpty()) {
                    binding.etPasswordSecond.requestFocus()
                    binding.etPasswordSecond.error = "Please enter your password"
                }
            }
            else if(!binding.etPasswordFirst.text.toString().equals(binding.etPasswordSecond.text.toString())) {
                binding.etPasswordFirst.requestFocus()
                binding.etPasswordSecond.requestFocus()
                binding.etPasswordFirst.error = "Incorrect password"
                binding.etPasswordSecond.error = "Incorrect password"
            }
            else {
                onProfileCreated(getProfileSignIn())
                foundSignIn = false
            }
        }
    }

    private fun loadItemsInBackground() {
        thread {
            database.profileDao().getAll()
        }
    }

    override fun onBackPressed() { }

    private fun getExistProfile(profile: Profile) {
        thread {
            var num = database.profileDao().getProfileWithoutPassword(profile.email)
            if(num == 1) {
                var num2 = database.profileDao().getProfile(profile.email, profile.password)
                if(num2 == 1) {
                    runOnUiThread() {
                        onEmailSelected(profile.email)
                    }
                }
                else {
                    runOnUiThread() {
                        binding.etPasswordLogin.requestFocus()
                        binding.etPasswordLogin.error = "Wrong password"
                    }
                }
            }
            else {
                runOnUiThread() {
                    binding.etEmailAddressLogin.requestFocus()
                    binding.etEmailAddressLogin.error = "This email has not signed yet"
                }
            }
        }
    }

    @Throws(RuntimeException::class)
    fun onProfileCreated(newItem: Profile) {
        thread {
            try{
                database.profileDao().insert(newItem)
            }
            catch(e: RuntimeException) {
                runOnUiThread() {
                    foundSignIn = true
                    binding.etEmailAddressReg.requestFocus()
                    binding.etEmailAddressReg.error = "This email has already signed"
                }
            }
        }
    }

    private fun getProfileSignIn() = Profile(
        email = binding.etEmailAddressReg.text.toString(),
        password = binding.etPasswordFirst.text.toString()
    )

    private fun getProfileLogIn() = Profile(
        email = binding.etEmailAddressLogin.text.toString(),
        password = binding.etPasswordLogin.text.toString()
    )
}
