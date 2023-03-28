package com.example.test_task



import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.test_task.databinding.TestBinding



class MainActivity : AppCompatActivity() {
    lateinit var b: TestBinding

    private var cell_0 = mutableListOf<Int>(99,99,99,99,99,99)
    private var cell_1 = mutableListOf<Int>(99,99,99,99,99,99)
    private var cell_2 = mutableListOf<Int>(99,99,99,99,99,99)
    private var cell_3 = mutableListOf<Int>(99,99,99,99,99,99)
    private var cell_4 = mutableListOf<Int>(99,99,99,99,99,99)
    private var cell_5 = mutableListOf<Int>(99,99,99,99,99,99)
    private var cell_6 = mutableListOf<Int>(99,99,99,99,99,99)
   var cell = arrayOf(cell_0.toIntArray(),cell_1.toIntArray(),cell_2.toIntArray(),cell_3.toIntArray(),
    cell_4.toIntArray(),cell_5.toIntArray(),cell_6.toIntArray())

    var sum0=0
    var sum1=0
    var sum2=0
    var sum3=0
    var sum4=0
    var sum5=0
    var sum6=0
    lateinit var sumMap :Map<String,Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = TestBinding.inflate(layoutInflater)
        setContentView(b.root)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        init()

    }


    //проверка значения в ячейке
    private fun check_cells(zn:EditText,row:Int,col:Int){
        val text=zn.text.toString()
        if(text!="") {
            val count: Int = Integer.parseInt(text)
            if(count in (0..5)){
                when (row){
                    0->{cell_0[col]=count
                    sum0+=count}
                    1->{cell_1[col]=count
                        sum1+=count}
                    2->{cell_2[col]=count
                        sum2+=count}
                    3->{cell_3[col]=count
                        sum3+=count
                        }
                    4->{cell_4[col]=count
                        sum4+=count
                    }
                    5->{cell_5[col]=count
                        sum5+=count
                        }
                    6->{cell_6[col]=count
                        sum6+=count}
                }
                zn.setTextColor(Color.rgb(0, 0, 0))
                zn.clearFocus()
                zn.isCursorVisible = false

            } else {
                Toast.makeText(this, "Введите число от 0 до 5", Toast.LENGTH_SHORT).show()
                zn.setTextColor(Color.rgb(255, 0, 0))
            }
        }
    }

    //проверка всей талицы
    fun check_tabl():Boolean{
        cell = arrayOf(cell_0.toIntArray(),cell_1.toIntArray(),cell_2.toIntArray(),cell_3.toIntArray(),
            cell_4.toIntArray(),cell_5.toIntArray(),cell_6.toIntArray())
        cell.forEach {
            it.forEach {
                if(it>5 || it<0){
                    return false
                }
            }
        }
       return true

    }

    //проверка заполнения строки
    fun check_row(c:List<Int>):String{
        c.forEach{
            if(it>5 || it<0){
                return ""
            }
            else{
                if (check_tabl()) {
                    sumMap = mutableMapOf("1" to sum0, "2" to sum1,"3" to sum2,"4" to sum3,"5" to sum4,"6" to sum5,"7" to sum6)
                    place(sumMap)

                }
            }
        }
        return c.sum().toString()
    }


    //присвоение места
    fun place(map:Map<String,Int>)= with(b){

        val sortedMap = map.toList().sortedByDescending { (k, v) -> v }.toMap()
        val entries: List<String> = sortedMap.keys.toList()
        Log.d("my","$entries")
        Log.d("my","$sortedMap")
        val one=entries.indexOf("1")+1
        val two=entries.indexOf("2")+1
        val three=entries.indexOf("3")+1
        val four=entries.indexOf("4")+1
        val five=entries.indexOf("5")+1
        val six=entries.indexOf("6")+1
        val seven=entries.indexOf("7")+1
        b.m0.text=one.toString()
        b.m1.text=two.toString()
        b.m2.text=three.toString()
        b.m3.text=four.toString()
        b.m4.text=five.toString()
        b.m5.text=six.toString()
        b.m6.text=seven.toString()

    }

    fun key(editText:EditText):Boolean{
        editText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                // if the event is a key down event on the enter button
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    return true
                }
                return false
            }

        })
            return false
    }

    //слушатель изменений
    fun init(){
        b.k10.requestFocus()
        b.k10.isCursorVisible=true

        b.k10.doOnTextChanged { text, start, before, count ->

            check_cells(b.k10,1,0)
            b.s1.text=check_row(cell_1)
            b.k20.requestFocus()
            b.k20.isFocusable=true
            b.k20.isCursorVisible=true

        }

        b.k20.doOnTextChanged { text, start, before, count ->
            check_cells(b.k20,2,0)
            b.s2.text=check_row(cell_2)
            b.k30.requestFocus()
            b.k30.isCursorVisible=true}

        b.k30.doOnTextChanged { text, start, before, count ->
            check_cells(b.k30,3,0)
            b.s3.text=check_row(cell_3)
            b.k40.requestFocus()
            b.k40.isCursorVisible=true}

        b.k40.doOnTextChanged { text, start, before, count ->
            check_cells(b.k40,4,0)
            b.s4.text=check_row(cell_4)
            b.k50.requestFocus()
            b.k50.isCursorVisible=true}

        b.k50.doOnTextChanged { text, start, before, count ->
            check_cells(b.k50,5,0)
            b.s5.text=check_row(cell_5)
            b.k60.requestFocus()
            b.k60.isCursorVisible=true}

        b.k60.doOnTextChanged { text, start, before, count ->
            check_cells(b.k60,6,0)
            b.s6.text=check_row(cell_6)
            b.k01.requestFocus()
            b.k01.isCursorVisible=true}

        b.k01.doOnTextChanged { text, start, before, count ->
            check_cells(b.k01,0,0)
            b.s0.text=check_row(cell_0)
            b.k21.requestFocus()
            b.k21.isCursorVisible=true}

        b.k21.doOnTextChanged { text, start, before, count ->
            check_cells(b.k21,2,1)
            b.s2.text=check_row(cell_2)
            b.k31.requestFocus()
            b.k31.isCursorVisible=true}

        b.k31.doOnTextChanged { text, start, before, count ->
            check_cells(b.k31,3,1)
            b.s3.text=check_row(cell_3)
            b.k41.requestFocus()
            b.k41.isCursorVisible=true}

        b.k41.doOnTextChanged { text, start, before, count ->
            check_cells(b.k41,4,1)
            b.s4.text=check_row(cell_4)
            b.k51.requestFocus()
            b.k51.isCursorVisible=true}

        b.k51.doOnTextChanged { text, start, before, count ->
            check_cells(b.k51,5,1)
            b.s5.text=check_row(cell_5)
            b.k61.requestFocus()
            b.k61.isCursorVisible=true}

        b.k61.doOnTextChanged { text, start, before, count ->
            check_cells(b.k61,6,1)
            b.s6.text=check_row(cell_6)
            b.k02.requestFocus()
            b.k02.isCursorVisible=true}

        b.k02.doOnTextChanged { text, start, before, count ->
            check_cells(b.k02,0,1)
            b.s0.text=check_row(cell_0)
            b.k12.requestFocus()
            b.k12.isCursorVisible=true}

        b.k12.doOnTextChanged { text, start, before, count ->
            check_cells(b.k12,1,1)
            b.s1.text=check_row(cell_1)
            b.k32.requestFocus()
            b.k32.isCursorVisible=true}

        b.k32.doOnTextChanged { text, start, before, count ->
            check_cells(b.k32,3,2)
            b.s3.text=check_row(cell_3)
            b.k42.requestFocus()
            b.k42.isCursorVisible=true}

        b.k42.doOnTextChanged { text, start, before, count ->
            check_cells(b.k42,4,2)
            b.s4.text=check_row(cell_4)
            b.k52.requestFocus()
            b.k52.isCursorVisible=true}

        b.k52.doOnTextChanged { text, start, before, count ->
            check_cells(b.k52,5,2)
            b.s5.text=check_row(cell_5)
            b.k62.requestFocus()
            b.k62.isCursorVisible=true}

        b.k62.doOnTextChanged { text, start, before, count ->
            check_cells(b.k62,6,2)
            b.s6.text=check_row(cell_6)
            b.k03.requestFocus()
            b.k03.isCursorVisible=true}

        b.k03.doOnTextChanged { text, start, before, count ->
            check_cells(b.k03,0,2)
            b.s0.text=check_row(cell_0)
            b.k13.requestFocus()
            b.k13.isCursorVisible=true}

        b.k13.doOnTextChanged { text, start, before, count ->
            check_cells(b.k13,1,2)
            b.s1.text=check_row(cell_1)
            b.k23.requestFocus()
            b.k23.isCursorVisible=true}

        b.k23.doOnTextChanged { text, start, before, count ->
            check_cells(b.k23,2,2)
            b.s2.text=check_row(cell_2)
            b.k43.requestFocus()
            b.k43.isCursorVisible=true}

        b.k43.doOnTextChanged { text, start, before, count ->
            check_cells(b.k43,4,3)
            b.s4.text=check_row(cell_4)
            b.k53.requestFocus()
            b.k53.isCursorVisible=true}

        b.k53.doOnTextChanged { text, start, before, count ->
            check_cells(b.k53,5,3)
            b.s5.text=check_row(cell_5)
            b.k63.requestFocus()
            check_tabl()
            b.k63.isCursorVisible=true}

        b.k63.doOnTextChanged { text, start, before, count ->
            check_cells(b.k63,6,3)
            b.s6.text=check_row(cell_6)
            b.k04.requestFocus()
            b.k04.isCursorVisible=true}

        b.k04.doOnTextChanged { text, start, before, count ->
            check_cells(b.k04,0,3)
            b.s0.text=check_row(cell_0)
            b.k14.requestFocus()
            b.k14.isCursorVisible=true}

        b.k14.doOnTextChanged { text, start, before, count ->
            check_cells(b.k14,1,3)
            b.s1.text=check_row(cell_1)
            b.k24.requestFocus()
            b.k24.isCursorVisible=true}

        b.k24.doOnTextChanged { text, start, before, count ->
            check_cells(b.k24,2,3)
            b.s2.text=check_row(cell_2)
            b.k34.requestFocus()
            b.k34.isCursorVisible=true}

        b.k34.doOnTextChanged { text, start, before, count ->
            check_cells(b.k34,3,3)
            b.s3.text=check_row(cell_3)
            b.k54.requestFocus()
            b.k54.isCursorVisible=true}

        b.k54.doOnTextChanged { text, start, before, count ->
            check_cells(b.k54,5,4)
            b.s5.text=check_row(cell_5)
            b.k64.requestFocus()
            b.k64.isCursorVisible=true}

        b.k64.doOnTextChanged { text, start, before, count ->
            check_cells(b.k64,6,4)
            b.s6.text=check_row(cell_6)
            b.k05.requestFocus()
            b.k05.isCursorVisible=true}

        b.k05.doOnTextChanged { text, start, before, count ->
            check_cells(b.k05,0,4)
            b.s0.text=check_row(cell_0)
            b.k15.requestFocus()
            b.k15.isCursorVisible=true}

        b.k15.doOnTextChanged { text, start, before, count ->
            check_cells(b.k15,1,4)
            b.s1.text=check_row(cell_1)
            b.k25.requestFocus()
            b.k25.isCursorVisible=true}

        b.k25.doOnTextChanged { text, start, before, count ->
            check_cells(b.k25,2,4)
            b.s2.text=check_row(cell_2)
            b.k35.requestFocus()
            b.k35.isCursorVisible=true}

        b.k35.doOnTextChanged { text, start, before, count ->
            check_cells(b.k35,3,4)
            b.s3.text=check_row(cell_3)
            b.k45.requestFocus()
            b.k45.isCursorVisible=true}

        b.k45.doOnTextChanged { text, start, before, count ->
            check_cells(b.k45,4,4)
            b.s4.text=check_row(cell_4)
            b.k65.requestFocus()
            b.k65.isCursorVisible=true}

        b.k65.doOnTextChanged { text, start, before, count ->
            check_cells(b.k65,6,5)
            b.s6.text=check_row(cell_6)
            b.k06.requestFocus()
            b.k06.isCursorVisible=true}

        b.k06.doOnTextChanged { text, start, before, count ->
            check_cells(b.k06,0,5)
            b.s0.text=check_row(cell_0)
            b.k16.requestFocus()
            b.k16.isCursorVisible=true}

        b.k16.doOnTextChanged { text, start, before, count ->
            check_cells(b.k16,1,5)
            b.s1.text=check_row(cell_1)
            b.k26.requestFocus()
            b.k26.isCursorVisible=true}

        b.k26.doOnTextChanged { text, start, before, count ->
            check_cells(b.k26,2,5)
            b.k36.requestFocus()
            b.s2.text=check_row(cell_2)
            b.k36.isCursorVisible=true}

        b.k36.doOnTextChanged { text, start, before, count ->
            check_cells(b.k36,3,5)
            b.k46.requestFocus()
            b.s3.text=check_row(cell_3)
            b.k46.isCursorVisible=true}

        b.k46.doOnTextChanged { text, start, before, count ->
            check_cells(b.k46,4,5)
            b.k56.requestFocus()
            b.s4.text=check_row(cell_4)
            b.k56.isCursorVisible=true}

        b.k56.doOnTextChanged { text, start, before, count ->
            check_cells(b.k56,5,5)
            b.k10.requestFocus()
            b.s5.text=check_row(cell_5)
            b.k10.isCursorVisible=true
        }

    }


}


