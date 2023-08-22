package com.example.carrocompras_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrocompras_kotlin.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdaptadorProducto

    var listaProductos = ArrayList<Producto>()
    var carroCompras = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        agregarProductos()

        setupRecyclerView()

        leerSharedPreferences()
    }

    fun setupRecyclerView() {
        //binding.rvListaProductos.layoutManager = LinearLayoutManager(this)
        binding.rvListaProductos.layoutManager = GridLayoutManager(this, 2)
        adapter = AdaptadorProducto(this, binding.tvCantProductos, binding.btnVerCarro, listaProductos, carroCompras)
        binding.rvListaProductos.adapter = adapter
    }

    fun agregarProductos() {
        listaProductos.add(Producto("1", R.drawable.reebokroyal,"Reebok Royal", 76.789))
        listaProductos.add(Producto("2", R.drawable.puma_argentina, "Puma Argentina", 36.799))
        listaProductos.add(Producto("3", R.drawable.new_balance_velcro, "New Balance Velcro", 37.899))
        listaProductos.add(Producto("4", R.drawable.new_balance_soft,  "NEW BALANCE Soft", 43.999))
        listaProductos.add(Producto("5", R.drawable.zapatillas_arizona, "ZAPATILLAS - ARIZONA", 59.999))
        listaProductos.add(Producto("6", R.drawable.adidas_top, "Adidas Top", 22.559))
        listaProductos.add(Producto("7", R.drawable.new_balance_550_air, "New Balance 550 Air", 56.999))
        listaProductos.add(Producto("8", R.drawable.puma_tarrenz_sb_ii, "Puma TARRENZ SB II", 64.999))
        listaProductos.add(Producto("9", R.drawable.nike_air_jordan_5_retro, "Nike Air Jordan 5 retro", 222.999))
        listaProductos.add(Producto("10", R.drawable.nike_air_jordan_1_mid, "Nike Air Jordan 1 Mid", 129.999))
    }

    fun leerSharedPreferences() {
        val sp = this.getSharedPreferences("carro_compras", MODE_PRIVATE)

        val jsonString = sp.getString("productos", "")

        val jsonArray = JSONArray(jsonString)

        if (jsonArray != null) {
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                carroCompras.add(
                    Producto(
                    jsonObject.getString("idProducto"),
                    jsonObject.getInt("fotoProducto"),
                    jsonObject.getString("nomProducto"),
                    jsonObject.getDouble("precio")
                )
                )
            }
        }
    }
}
