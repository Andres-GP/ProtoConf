package com.protompany.protoconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.protompany.protoconf.R
import com.protompany.protoconf.model.Author
import com.protompany.protoconf.model.Conference
import com.protompany.protoconf.view.adapter.AuthorsAdapter
import com.protompany.protoconf.view.adapter.AuthorsListener
import com.protompany.protoconf.viewmodel.AuthorsViewModal
import kotlinx.android.synthetic.main.fragment_authors.*
import kotlinx.android.synthetic.main.fragment_schedule.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AuthorsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthorsFragment : Fragment(), AuthorsListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var authorsAdapter: AuthorsAdapter
    private lateinit var  viewModel: AuthorsViewModal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AuthorsViewModal::class.java)
        viewModel.refresh()

        authorsAdapter = AuthorsAdapter(this)

        rvAuthors.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = authorsAdapter
        }
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.listAuthors.observe(viewLifecycleOwner, Observer<List<Author>> { author ->
            author.let {
                authorsAdapter.updateData(author)
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if (it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AuthorsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AuthorsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAuthorsClicked(author: Author, position: Int) {
        val bundle = bundleOf("author" to author)
        findNavController().navigate(R.id.authorsDetailFragmentDialog, bundle)
    }


}