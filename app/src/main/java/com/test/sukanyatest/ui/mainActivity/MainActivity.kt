package com.test.sukanyatest.ui.mainActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.test.sukanyatest.QuoteApplication
import com.test.sukanyatest.R
import com.test.sukanyatest.api.quoteService
import com.test.sukanyatest.api.retrofitHelper
import com.test.sukanyatest.data.models.quotes.Result
import com.test.sukanyatest.data.repository.QuoteRepository
import com.test.sukanyatest.data.repository.Response
import com.test.sukanyatest.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewM:MainViewModel;
   lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)



    val repository= (application as QuoteApplication).quoteRespository


        //Instantiating viewModel
        viewM=ViewModelProvider(this,MainViewModelFactory(repository,this))
            .get(MainViewModel::class.java)


        //Observing Livedata in viewModel
        viewM.dataa.observe(this,{

//            binding.result=it.results.get(0)


       when(it){
           is Response.Success->{
               val author=it.data?.results?.get(0)?.author
               binding.textView.text=author
               binding.textView2.text=author
               binding.textView3.text=author
               binding.textView4.text=author

               it.data?.let{


               Log.d("success","success loaded data from network")

           }
           }
           is Response.Error->{  val error=it.ErrorMessage
               binding.textView.text=error
               binding.textView.text=error
               binding.textView.text=error
               binding.textView.text=error
           Log.d("fail","data failed to load from network")
           }


           is Response.Loading->{
               Toast.makeText(this,"failed to load",Toast.LENGTH_LONG).show()

           }
           else -> {
Toast.makeText(this,"nothing happedned",Toast.LENGTH_LONG).show()
           }
       }

            })

        binding.button.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                viewM.getQuoteApi()
            }
        }



    }
}