package com.example.demovm

import android.os.AsyncTask
import android.util.Log
import androidx.databinding.ObservableField
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

private const val TAG = "AsycTaskHTTP"
class AsyncTaskHttpDemo : AsyncTask<ObservableField<String>, Integer, ObservableField<String>>() {
    var jsonObject = JSONObject()
    val myUrl = URL("http://192.168.132.136/demoVM.php")
    val connection = myUrl.openConnection() as HttpURLConnection
    lateinit var getStr: String

    private val urlConnection: HttpURLConnection? = null



    override fun onPreExecute() {
        super.onPreExecute()
        Log.i(TAG, "onPreExecute: ")
    }
    override fun doInBackground(vararg p0: ObservableField<String>?): ObservableField<String> {
        Log.i(TAG, "doInBackground: p0[0]"+p0[0]?.get())
        this.getStr = p0[0]?.get()!!

        jsonObject.put("key100",this.getStr)
        Log.i(TAG, "doInBackground: json: "+ jsonObject.toString())

        //http setting
        connection.requestMethod = "POST"
        connection.connectTimeout = 10000
        connection.readTimeout = 10000
        connection.doInput = true
        connection.doOutput = true

        //Log.i(TAG, "doInBackground: error connect: "+connection.errorStream.toString())
        var postStr: ByteArray = jsonObject.toString().toByteArray()
        Log.i(TAG, "doInBackground: postStr: "+postStr)
        connection.setRequestProperty("charset", "utf-8")
        connection.setRequestProperty("Content-lenght", postStr.size.toString())
        connection.setRequestProperty("Content-Type", "application/json")

        try {
            val outputStream: DataOutputStream = DataOutputStream(connection.outputStream)
            outputStream.write(postStr)
            outputStream.flush()
            outputStream.close()
        }catch (e: Exception){
            Log.i(TAG, "doInBackground: outputException:" + e.toString())
        }
        try {
            val inputStream : DataInputStream = DataInputStream(connection.inputStream)
            val reader: BufferedReader = BufferedReader(InputStreamReader(inputStream))
            this.getStr = this.getStr + " from WebAPI: "+reader.readLine()
            Log.i(TAG, "doInBackground: inputStream "+ reader.readLine() )
        }catch (e:Exception){
            Log.i(TAG, "doInBackground: inputException:" + e.toString())
        }


        var obstr = ObservableField<String>()
        obstr.set(this.getStr)

        return obstr
    }

    override fun onPostExecute(result: ObservableField<String>?) {
        super.onPostExecute(result)
        Log.i(TAG, "onPostExecute: "+result)
    }


}