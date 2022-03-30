package org.d3if2086.echo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if2086.echo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { echo() }

        binding.button2.setOnClickListener { resetButton() }

    }

    private fun echo() {
        val nama_people = binding.namaEditText.text.toString()
        if (TextUtils.isEmpty(nama_people)) {
            Toast.makeText(this, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val berat = binding.beratEditText.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(this, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val tinggi = binding.tinggiEditText.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(this, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tinggiCm = tinggi.toFloat() / 100
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val isMale = selectedId == R.id.priaRadioButton
        val bmi = berat.toFloat() / (tinggiCm * tinggiCm)
        val kategori = getKategori(bmi, isMale)

        binding.namaTextView.text = getString(R.string.nama, nama_people)
        binding.kategoriTextView.text = getString(R.string.kategori_x, kategori)
    }

    private fun getKategori(bmi: Float, isMale: Boolean): String {
        val stringRes = if (isMale) {
            when {
                bmi < 20.5 -> R.string.tidak_lulus
                else -> R.string.lulus
            }
        } else {
            when {
                bmi < 18.5 -> R.string.tidak_lulus
                else -> R.string.lulus
            }
        }
        return getString(stringRes)
    }

    private fun resetButton(){
        binding.namaEditText.text?.clear()
        binding.kendalaEditText.text?.clear()
        binding.beratEditText.text?.clear()
        binding.tinggiEditText.text?.clear()
        binding.namaTextView.text = ""
        binding.kategoriTextView.text = ""
    }
}