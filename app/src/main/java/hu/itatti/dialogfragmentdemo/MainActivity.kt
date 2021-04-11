package hu.itatti.dialogfragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.itatti.dialogfragmentdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), QueryFragment.OnQueryFragmentAmswer, SelectFruitFragment.OptionsFragmentInterface {
    private lateinit var binding: ActivityMainBinding

    companion object{
        val KEY_MSG = "KEY_MSG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDialogMessage.setOnClickListener {
            val queryFragment = QueryFragment()
            queryFragment.isCancelable=false
            val bundle=Bundle()
            bundle.putString(KEY_MSG,"Hello Advanced Android")
            queryFragment.arguments=bundle

            queryFragment.show(supportFragmentManager,"QueryFragment")
        }

        binding.btnDialogList.setOnClickListener {
            SelectFruitFragment().show(supportFragmentManager,SelectFruitFragment.TAG)
        }

    }

    override fun onPositiveSelected(text: String) {
       binding.tvData.text = "ok"
    }

    override fun onNegativeSelected() {
        binding.tvData.text = "No"
    }

    override fun omOptionsFragmentResult(fruit: String) {
        binding.tvData.text = fruit
    }
}