package com.gini.qpop

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.gini.qpop.adapter.InfiniteRecyclerAdapter
import com.gini.qpop.model.Data
import com.gini.qpop.util.AppUtils
import com.google.gson.Gson
import org.json.JSONException
import java.io.IOException

class SampleActivity : AppCompatActivity() {

    private lateinit var infiniteViewPager: ViewPager2
    private lateinit var infiniteRecyclerAdapter: InfiniteRecyclerAdapter

    private var dataList: MutableList<Data> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_questions)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        // getting the required sample data for filling the ViewPager
        getSampleData()

        // setting up the infinite ViewPager
        infiniteViewPager = findViewById(R.id.infiniteViewPager)
        infiniteRecyclerAdapter = InfiniteRecyclerAdapter(dataList)
        infiniteViewPager.adapter = infiniteRecyclerAdapter

        // setting the current item of the infinite ViewPager to the actual first element
        infiniteViewPager.currentItem = 1

        // function for registering a callback to update the ViewPager
        // and provide a smooth flow for infinite scroll
        onInfinitePageChangeCallback(dataList.size + 2)
    }

    private fun getSampleData() {
        val data = getDataFromJson()
        for (i in 0 until getDataFromJson().size) {
            dataList.add(Data(data.get(i), getRandomColor()))
        }
    }

    private fun getRandomColor(): String {
        val colors = listOf(
            "#c339c6",
            "#89288b",
            "#3bc639",
            "#8b8028",
            "#35378b",
            "#a4c639",
            "#c65439",
        )
        return colors.random()

    }


    //function to to read from json file in assets folder using the JsonFileReader util class
    private fun getDataFromJson():  MutableList<String> {
        try {
//            val jsonObject = JsonFileReader.readJsonFile(this, "alternative.json")
//            val question = getLevel()?.let { jsonObject.getJSONArray(it) }
//            return (0 until question!!.length()).map { question.getString(it) }

            val jsonObject = AppUtils.readJsonFile(this, "alternative.json")
            val question = jsonObject.optString(getLevel())
            val gson = Gson()
            val questions = gson.fromJson<List<*>>(
                question,
                MutableList::class.java
            )
            return questions as MutableList<String>
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: JSONException) {
            throw RuntimeException(e)
        }
    }

    private fun getLevel(): String? {
        return intent.getStringExtra("level")
    }
    private fun onInfinitePageChangeCallback(listSize: Int) {
        infiniteViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (infiniteViewPager.currentItem) {
                        listSize - 1 -> infiniteViewPager.setCurrentItem(1, false)
                        0 -> infiniteViewPager.setCurrentItem(listSize - 2, false)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position != 0 && position != listSize - 1) {
                    // pageIndicatorView.setSelected(position-1)
                }
            }
        })
    }

}
