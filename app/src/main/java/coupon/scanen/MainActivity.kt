package coupon.scanen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

const val SCANNER = "scanner"

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.et)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    if (p0.length >= 59) {
                        val qr = editText.text.toString()
                        val intent = Intent(this@MainActivity, ScanActivity::class.java)
                        intent.putExtra(SCANNER, qr)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        editText.addTextChangedListener(textWatcher)

        if (editText.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Please connect to scanner in order to fill the text field",
                Toast.LENGTH_LONG
            ).show()
        } else {
            val qr = editText.text.toString()
            val intent = Intent(this, ScanActivity::class.java)
            intent.putExtra(SCANNER, qr)
            startActivity(intent)
        }
    }

}