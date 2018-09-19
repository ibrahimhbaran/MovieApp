package com.dex.movieapp.view.activitiy

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.dex.movieapp.BR


import com.dex.movieapp.R
import com.dex.movieapp.data.source.MoviesRepository
import com.dex.movieapp.databinding.ActivityMainBinding
import com.dex.movieapp.utils.ItemDecorator
import com.dex.movieapp.utils.Prefs
import com.dex.movieapp.utils.ViewUtils
import com.dex.movieapp.view.adapter.MoviesAdapter
import com.dex.movieapp.viewmodels.MoviesViewModel
import com.dex.movieapp.viewmodels.SearchViewModel


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding

    private val moviesAdapter = MoviesAdapter(this)

    private val searchViewModel = SearchViewModel()

    private var moviesViewModel: MoviesViewModel? = null

    private var prefs: Prefs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = Prefs(this)

        initBinding()

        checkCache()


        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)


        binding.searchViewModel?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

                if (propertyId == BR.searchTerm){
                    val svm = sender as SearchViewModel
                    moviesViewModel?.loadMovies(svm.searchTerm)?.observe(this@MainActivity, Observer {
                        it?.run {
                            binding.adapter!!.updateMovies(it)
                        }
                    })
                }

            }

        })

        binding.searchViewModel?.searchTerm =""


    }

    /**
     *  init databinding components
     */

    private fun initBinding() {

        val gridColumns = ViewUtils.calculateNoOfColumns(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.adapter = moviesAdapter

        binding.layoutManager = GridLayoutManager(this, gridColumns)

        binding.dividerItemDecoration = ItemDecorator(10, gridColumns)

        binding.searchViewModel = searchViewModel

    }

    /**
     * application will refresh cache if last call to server has done since last 10 minutes
     * normally would be better call this with a scheduler. for test purpose will be called on create activity each time
     */
    private  fun checkCache(){
        // set cache flag for making a new call to server
       if(System.currentTimeMillis() >prefs!!.cacheTime ){

           MoviesRepository.getInstance(this).cacheIsDirty = true

           Log.v(TAG, "start clearing cache ")

       }

    }
}


