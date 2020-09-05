package com.example.andelaproject.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.andelaproject.R
import com.example.andelaproject.repository.Repository
import com.example.andelaproject.util.Resource
import kotlinx.android.synthetic.main.submit_screen.*
import kotlinx.android.synthetic.main.submit_screen.view.*

class SubmitFragment: Fragment(R.layout.submit_screen) {

    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).viewModel
        val viewModelProviderFactory = MainViewModelProviderFactory(
            Repository()
        )
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        view.submitToolbar
            .setupWithNavController(navController, appBarConfiguration)



        btnSubmit.setOnClickListener {
            if (etFirstName.text.isEmpty() || etLastName.text.isEmpty() || etEmailAddress.text.isEmpty() || etGitHubLink.text.isEmpty()) {
                val sweetAlert = SweetAlertDialog(activity)
                sweetAlert.titleText = "Input All Fields!"
                sweetAlert.show()

            } else {
                val sweetAlertDialog = SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                sweetAlertDialog.titleText = "Are you sure?"
                sweetAlertDialog.confirmText = "Yes"
                sweetAlertDialog.show()
                sweetAlertDialog.setConfirmClickListener {
                viewModel.getPostDetail(etFirstName.text.toString(), etLastName.text.toString(), etEmailAddress.text.toString(),
                etGitHubLink.text.toString())
                }
            }
        }



        viewModel.postDetail.observe(viewLifecycleOwner, Observer {response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        val sweetAlertDialog1 = SweetAlertDialog(
                            activity, SweetAlertDialog.SUCCESS_TYPE
                        )
                        sweetAlertDialog1.titleText = "Good Job!"
                        sweetAlertDialog1.contentText = "Submission Successful"
                        sweetAlertDialog1.show()

                    }
                }
                is Resource.Error -> {
                    response.data?.let {
                        val sweetAlertDialog2 = SweetAlertDialog(
                            activity,
                            SweetAlertDialog.ERROR_TYPE)
                        sweetAlertDialog2.titleText = "Oops..."
                        sweetAlertDialog2.contentText = "Submission not Successful, Try again"
                        sweetAlertDialog2.show()
                    }
                }
            }

        })


            }


        }