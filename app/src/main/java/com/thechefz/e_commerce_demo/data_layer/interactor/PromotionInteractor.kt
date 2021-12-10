package com.thechefz.e_commerce_demo.data_layer.interactor

import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import com.thechefz.e_commerce_demo.data_layer.entities.ProductEntity
import com.thechefz.e_commerce_demo.data_layer.entities.PromotionEntity
import com.thechefz.e_commerce_demo.data_layer.repositiries.CategoryRepo
import com.thechefz.e_commerce_demo.utils.extensions.*
import com.thechefz.e_commerce_demo.utils.extensions.LiveObj.promotionEntity
import java.util.*

class PromotionInteractor(private val categoryRepo: CategoryRepo) {

    fun checkPromotion(
        categoryId: String?,
        timeStamp: String?,
        minQty: String?,
        maxQty: String?,
        price: String?,
        finish: (String) -> Unit
    ) {
        var message = "Invalid Promotion, please try another link"
        var category: CategoryEntity? = null
        // get category by id
        categoryRepo.getCategories({ item ->
            category = item.findLast { it.id == categoryId ?: "" }
        }, { })

        // check if category found or not
        if (category == null) {
            finish(message)
            return
        }

        // check if date is valid
        if (validDateForPromotion(timeStamp.fromTimestampToDate()).not()) {
            finish(message)
            return
        }

        // check if promotion have quantity and price value
        if (isValidPrice(price) && isValidQty(minQty, maxQty)) {
            promotionEntity = PromotionEntity(
                minQty.intValue(),
                maxQty.intValue(),
                price.doubleValue(),
                categoryId.value()
            )
            message =
                "Congratulation, you enjoy $price discount on ${category?.name} valid from today to ${
                    timeStamp.toDateTime("dd-MM-yyyy")
                }"
        }
        finish(message)
        return

    }

    private fun isValidPrice(price: String?): Boolean {
        return price.isNullOrEmpty().not()
    }

    private fun isValidQty(min: String?, max: String?): Boolean {
        return min.intValue() > 0 && max.intValue() > 0 && max.intValue() >= min.intValue()
    }

    private fun validDateForPromotion(date: Date): Boolean {
        // check if date after today and before 25-12-2021
        return date.after(Date()) && date.before(getMaxDate())
    }

    // i assume that max day is 25-12-2021
    private fun getMaxDate(): Date {
        val calender = Calendar.getInstance()
        calender.set(Calendar.DAY_OF_MONTH, 25)
        return calender.time
    }


    fun handlePromotion(
        product: ProductEntity,
        count: Int,
        productAfterPromotion: (item:ProductEntity) -> Unit,
        invalidData: (String?) -> Unit
    ) {
        // cal price
        val price = product.price.intValue() * count
        // set total price in object
        product.totalPrice = price.toString()
        // error msg
        var msg : String? = null

        promotionEntity?.let {
            // check product selected have same category in promotion
            if (product.categoryId == it.categoryId) {
                if (count > it.minQty && count <= it.maxQty) {
                    // set discount price
                    product.promotionPrice = it.discountAmount.toString()
                    // set price after discount
                    product.totalPriceAfterPromotion = (price - it.discountAmount).toString()
                    productAfterPromotion.invoke(product)
                    invalidData.invoke(null)
                    return
                } else {
                    msg = "Quantity must greater than ${it.minQty} or less than ${it.maxQty}"
                }
            }
        }
        invalidData.invoke(msg)
        product.totalPriceAfterPromotion = null
        product.promotionPrice = null
        productAfterPromotion.invoke(product)
    }
}