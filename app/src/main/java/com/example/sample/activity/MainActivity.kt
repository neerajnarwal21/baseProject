package com.example.sample.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.example.sample.R
import com.example.sample.utils.Const
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_custom.*
import java.util.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        setDrawer()
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        jumpToHome()

    }

    fun jumpToHome() {
//        supportFragmentManager.popBackStackImmediate(Const.ADD_STACK, FragmentManager.POP_BACK_STACK_INCLUSIVE)
////        drawer.closeDrawer(Gravity.START)
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.frame_home, HomeFragment())
//                .commit()
    }

    private fun setDrawer() {
//        val drawerData = ArrayList<DrawerData>()
//        drawerData.add(DrawerData("Home"))
//        drawerData.add(DrawerData("Logout"))
//        left_drawerRV.layoutManager = LinearLayoutManager(this)
//        left_drawerRV.adapter = DrawerAdapter(this, drawerData)
    }

//    fun onAdapterItemClick(drawerData: DrawerData) {
//        when (drawerData.title) {
//            "Home" -> jumpToHome()
//            "Logout" -> logoutUser(this)
//        }
//        drawer.closeDrawer(Gravity.START)
//    }
//
//    override fun onClick(v: View) {
//        when (v.id) {
//            R.id.backTBIV -> onBackPressed()
//            R.id.navTBIV -> {
//                hideSoftKeyboard()
//                drawer.openDrawer(Gravity.START)
//            }
//            R.id.closeTBIV -> {
//                val bldr = AlertDialog.Builder(this)
//                bldr.setTitle("Close !!")
//                bldr.setMessage("Are you sure to close ?")
//                bldr.setPositiveButton("Yes") { _, _ ->
//                    supportFragmentManager.popBackStackImmediate(Const.ADD_STACK, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//                    jumpToHome()
//                }
//                bldr.setNegativeButton("No") { _, _ -> }
//                bldr.create().show()
//            }
//            R.id.newTBIV -> supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frame_home, AddPhoneNumberFragment())
//                    .addToBackStack(Const.ADD_STACK)
//                    .commit()
//        }
//    }

    fun setToolbar(showDrawer: Boolean, text: String, showAddUser: Boolean, showClose: Boolean) {
        titleTBTV.text = text
        navTBIV.visibility = if (showDrawer) View.VISIBLE else View.GONE
//        backTBIV.visibility = if (showDrawer) View.GONE else View.VISIBLE
//        newTBIV.visibility = if (showAddUser) View.VISIBLE else View.GONE
//        closeTBIV.visibility = if (showClose) View.VISIBLE else View.GONE
//        closeTBIV.setImageResource(R.drawable.ic_close_white)
//        navTBIV.setOnClickListener(this)
//        backTBIV.setOnClickListener(this)
//        newTBIV.setOnClickListener(this)
//        closeTBIV.setOnClickListener(this)
    }

//    override fun onBackPressed() {
//        hideSoftKeyboard()
//        if (drawer.isDrawerOpen(Gravity.START)) {
//            drawer.closeDrawer(Gravity.START)
//        } else {
//            val frag = supportFragmentManager.findFragmentById(R.id.frame_home)
//            var toJumpFrag: Fragment? = null
//            when (frag) {
//                is Add1UserFragment -> jumpToHome()
//                is Add2AddressFragment -> toJumpFrag = Add1UserFragment()
//                is Add3BankDetailsFragment -> toJumpFrag = Add2AddressFragment()
//                is Add4BasicLoanDetailsFragment -> frag.onBackPressed()
//                is Add5GuarantorFragment -> frag.onBackPressed()
//                is Add6ReferencesFragment -> frag.onBackPressed()
//                is LoanReviewFragment -> toJumpFrag = when {
//                    loanDataManager.getLoanData().loanPlan.references > 0 -> Add6ReferencesFragment()
//                    loanDataManager.getLoanData().loanPlan.guarantor == "yes" -> Add5GuarantorFragment()
//                    else -> Add4BasicLoanDetailsFragment()
//                }
//            }
//            if (toJumpFrag != null) {
//                supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_home, toJumpFrag)
//                        .commit()
//            } else if (!(frag is Add4BasicLoanDetailsFragment
//                            || frag is Add5GuarantorFragment
//                            || frag is Add6ReferencesFragment)) {
//                if (supportFragmentManager.backStackEntryCount > 0) {
//                    supportFragmentManager.popBackStack()
//                } else if (frag is HomeFragment)
//                    showExit()
//                else {
//                    jumpToHome()
//                }
//            }
//        }
//    }
}
