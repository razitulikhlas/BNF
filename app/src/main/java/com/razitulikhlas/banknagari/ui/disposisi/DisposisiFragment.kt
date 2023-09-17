package com.razitulikhlas.banknagari.ui.disposisi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.razitulikhlas.banknagari.adapter.DataSkimAdapter
import com.razitulikhlas.banknagari.adapter.ProcessAdapter
import com.razitulikhlas.banknagari.databinding.FragmentDisposisiBinding
import com.razitulikhlas.banknagari.ui.dashboard.DashBoardViewModel
import com.razitulikhlas.banknagari.ui.permohonan.InsertApplicationActivity
import com.razitulikhlas.banknagari.ui.permohonan.OfficerViewModel
import com.razitulikhlas.core.data.source.remote.network.ApiResponse
import com.razitulikhlas.core.data.source.remote.response.DataItemSkim
import com.razitulikhlas.core.util.Constant
import com.razitulikhlas.core.util.Constant.LEVEL_CHECK
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class DisposisiFragment : Fragment(), DataSkimAdapter.DataSkimCallback{

    private val adapters: DataSkimAdapter by inject()
    private val pAdapters: ProcessAdapter by inject()
    private val viewModel : DashBoardViewModel by viewModel()


    private var _binding: FragmentDisposisiBinding? = null
    private val binding get() = _binding!!



    companion object {
        const val CATEGORY = "category"
        fun newInstance(category: String?): DisposisiFragment{
            val args = Bundle()
            args.putSerializable(CATEGORY, category)
            val fragment = DisposisiFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentDisposisiBinding.inflate(layoutInflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createFragment()
    }

    private fun createFragment() {
        Log.e("LEVEL", "createFragment: $LEVEL_CHECK", )

        if(LEVEL_CHECK> 2){
            binding.btnAdd.show()
        }else{
            binding.btnAdd.hide()
        }

        adapters.setListener(this@DisposisiFragment)
        pAdapters.setListener(this@DisposisiFragment)
        with(binding){
            btnAdd.setOnClickListener {
                val intent = Intent(requireActivity(), InsertApplicationActivity::class.java)
                startActivity(intent)
            }
        }
        binding.swipe.setOnRefreshListener{
            binding.swipe.isRefreshing = false

//            showLoading()
//            lifecycleScope.launch {
//                var info = Constant.NIK_CHECK
//                if(LEVEL_CHECK < 2){
//                    if(activity?.intent!!.getStringExtra("skim") != null){
//                        info =activity?.intent!!.getStringExtra("skim")!!
//                    }
//                }
//                viewModel.getDataSkim(info,LEVEL_CHECK)
//            }
//            getData()
        }
        getData()
    }

    private fun getData(){
        val category = arguments?.getString(CATEGORY)
        lifecycleScope.launch {
            viewModel.getDataSkim(Constant.NIK_CHECK,LEVEL_CHECK).observe(viewLifecycleOwner){
                when(it){
                    is ApiResponse.Success->{
                          val p = it.data.data?.filter {p->p.status == 1 }
                          val c = it.data.data?.filter {c->c.status == 0 }
                          val s = it.data.data?.filter {s->s.status == 2 }
                        if(category == "cancel"){
                            setData(c!!)
                        }else if(category == "proses"){
                            with(binding.rcv){
                                layoutManager = LinearLayoutManager(activity)
                                pAdapters.setData(p!!)
                                adapter = pAdapters
                            }
                        }else if(category == "selesai"){
                            setData(s!!)
                        }
                    }
                    is ApiResponse.Error->{

                    }
                    is ApiResponse.Empty->{

                    }
                }
            }
        }
//        viewModel.getProses().observe(viewLifecycleOwner){
//            setData(it)
//        }

//        if(category == "cancel"){
//            Log.e("TAG", "CANCEL: ", )
//                viewModel.getCancel().observe(viewLifecycleOwner){
//                    setData(it)
//                }
//
//
//        }else if(category == "proses"){
//            viewModel.getProses().observe(viewLifecycleOwner){
//                    setData(it)
//            }
//
//        }
//        else if(category == "selesai"){
//            viewModel.getSuccess().observe(viewLifecycleOwner){
//                setData(it)
//            }
//        }
        showPreviewData()
    }

  fun setData(data :List<DataItemSkim>){
        with(binding.rcv){
            layoutManager = LinearLayoutManager(activity)
            adapters.setData(data)
            adapter = adapters
//            hasFixedSize()
        }
    }

    private fun showPreviewData() {
        with(binding) {
            swipe.isRefreshing=false
            rcv.visibility = View.VISIBLE
            shimmers.visibility = View.GONE
            shimmers.stopShimmer()
        }
        binding.rcv.recycledViewPool.clear()

    }

    private fun showLoading() {
        with(binding) {
            rcv.visibility = View.GONE
            shimmers.visibility = View.VISIBLE
            shimmers.startShimmer()
        }
    }

    override fun onClick(item: DataItemSkim) {
        val intent  = Intent(activity,DetailDisposisiActivity::class.java)
        intent.putExtra("data",item)
        requireActivity().startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}