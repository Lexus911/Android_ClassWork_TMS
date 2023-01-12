package com.example.kotlinapp.presentation.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinapp.databinding.FragmentHomeBinding
import com.example.kotlinapp.utils.NavHelper.replaceGraph
import com.example.kotlinapp.utils.coroutines.CoroutinesExample
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        lifecycleScope.launch (Dispatchers.IO){ //по умолчанию на main dispatcher
//
//            viewModel.showUserData()
//
//            //смена потока с IO на MAIN
//            withContext(Dispatchers.Main){
//            binding.btnFinish.setOnClickListener {
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.activity_container, OnBoardingFragment())
//                    .commit()
//            }
//            }
//        }
//
//        CoroutineScope(Dispatchers.IO).launch{
//
//        }
//
//            // параллельное выполнение
//        lifecycleScope.launch (Dispatchers.IO){
//            //создаём переменные если ожидаем результат, если нет можно их не объявлять
//            val getData = async {viewModel.showUserData()}
//            val navigate = async {withContext(Dispatchers.Main){
//                binding.btnFinish.setOnClickListener {
//                    parentFragmentManager.beginTransaction()
//                        .replace(R.id.activity_container, OnBoardingFragment())
//                        .commit()  }
//                }
//            }
//        }

        CoroutinesExample().testCoroutineJoin()
        CoroutinesExample().testCoroutineCancel()

        viewModel.showUserData()

        binding.btnFinish.setOnClickListener {
            viewModel.toRecyclerView()
            }

        viewModel.nav.observe(viewLifecycleOwner){
            if(it != null) {
                replaceGraph(it)
            }
        }


        viewModel.userCreds.observe(viewLifecycleOwner){
            binding.tvUserCreds.text = "${it.userName} \n${it.userPassword}"
        }



    }
}