package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _isSpecial = MutableLiveData<Boolean>()
    val isSpecial: LiveData<Boolean> = _isSpecial

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }
    private val _flavorSummary = MutableLiveData<String>()
    val flavorSummary: LiveData<String> = _flavorSummary

    val dateOptions = getPickupOptions()
    val flavorOptions = mutableSetOf<String>()

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavor(desiredFlavor: String) {
        if (desiredFlavor == "Special") {
            setIsSpecial(false)
        }
        _flavor.value = desiredFlavor
    }

    fun setFlavorOptions(flavorOptions1: String) {
        flavorOptions.add(flavorOptions1)
    }

    fun removeFlavorOptions(flavorOptions2: String) {
        flavorOptions.remove(flavorOptions2)
    }

    fun setIsSpecial(isSp: Boolean) {
        _isSpecial.value = isSp
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()

    }


    fun setName(enteredName: String) {
        _name.value = enteredName
    }

    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }


    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _flavorSummary.value = ""
        _date.value = dateOptions[1]
        _price.value = 0.0
        _isSpecial.value = true
        flavorOptions.clear()
    }
}