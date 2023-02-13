package com.example.kotlinapp.presentation.view.home.items

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.App
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.FragmentSearchBinding
import com.example.kotlinapp.presentation.view.home.items.service.MusicPlayer
import com.example.kotlinapp.utils.BaseFragment
import com.example.kotlinapp.utils.rx.RxJavaExample
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.SchedulerSupport.IO
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject


class SearchFragment : BaseFragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels{viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Вызов observable RxJava
        RxJavaExample().observableJust1()
        RxJavaExample().observableFlatMap()
        RxJavaExample().observableZip()
        RxJavaExample().observableConcat()
        RxJavaExample().createObs(listOf<String>("IOS", "Android", "Flutter"))

        //Вызов subjects RxJava
        RxJavaExample().publishSubject()
        RxJavaExample().replaySubject()
        RxJavaExample().behaviorSubject()
        RxJavaExample().asyncSubject()




        (requireActivity().applicationContext as App).provideAppComponent().inject(this)
        //создание кнопки
//        val btn = Button(context)
//        btn.background = context?.getDrawable(R.drawable.avatar)
//        btn.text = context?.getString(R.string.app_name)
//        binding.root.addView(btn)

        AnimationUtils.loadAnimation(context, R.anim.rotate_anim).also {
            binding.btnStart.startAnimation(it)
        }

        binding.btnStart.setOnClickListener{

            requireActivity().startForegroundService(Intent(requireContext(), MusicPlayer::class.java))
        }

        val animatorSet = AnimatorSet()
        val y = ObjectAnimator.ofFloat(binding.btnStart,"scaleY",2f,1f)
        val x = ObjectAnimator.ofFloat(binding.btnStart,"scaleX",2f,1f)

        animatorSet.playTogether(x,y)
        animatorSet.start()

        val translate = ValueAnimator.ofFloat(2f, 1.5f)
        translate.addUpdateListener { animation ->
            val scale = animation.animatedValue.toString().toFloat()
            binding.btnStop.setScaleX(scale)
            binding.btnStop.setScaleY(scale)
        }
        translate.repeatCount = 50
        translate.start()


        binding.btnStop.setOnClickListener{
            requireActivity().stopService(Intent(requireContext(), MusicPlayer::class.java))
        }

        binding.search.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.findItem(p0?:"")
                return false
            }
        })

        viewModel.item.observe(viewLifecycleOwner){
            binding.tvDescription.text = it.description
            Picasso.get().load(Uri.parse(it.image)).into(binding.ivSearch)
        }
    }

}
