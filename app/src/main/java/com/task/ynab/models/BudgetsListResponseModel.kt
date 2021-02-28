package com.task.ynab.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetsListResponseModel(
    @Json(name = "data")
    val `data`: Data = Data()
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "budgets")
        val budgets: List<Budget> = listOf(),
        @Json(name = "default_budget")
        val defaultBudget: Any? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class Budget(
            @Json(name = "accounts")
            val accounts: List<Account> = listOf(),
            @Json(name = "currency_format")
            val currencyFormat: CurrencyFormat = CurrencyFormat(),
            @Json(name = "date_format")
            val dateFormat: DateFormat = DateFormat(),
            @Json(name = "first_month")
            val firstMonth: String = "",
            @Json(name = "id")
            val id: String = "",
            @Json(name = "last_modified_on")
            val lastModifiedOn: String = "",
            @Json(name = "last_month")
            val lastMonth: String = "",
            @Json(name = "name")
            val name: String = "",
            @Transient
            var totalAccounts:Int = 0,
            @Transient
            var isVisiable:Boolean = false

        ) {
            @JsonClass(generateAdapter = true)
            data class Account(
                @Json(name = "balance")
                val balance: Int = 0,
                @Json(name = "cleared_balance")
                var clearedBalance: Int = 0,
                @Json(name = "closed")
                val closed: Boolean = false,
                @Json(name = "deleted")
                val deleted: Boolean = false,
                @Json(name = "id")
                val id: String = "",
                @Json(name = "name")
                val name: String = "",
                @Json(name = "note")
                val note: Any? = null,
                @Json(name = "on_budget")
                val onBudget: Boolean = false,
                @Json(name = "transfer_payee_id")
                val transferPayeeId: String = "",
                @Json(name = "type")
                val type: String = "",
                @Json(name = "uncleared_balance")
                val unclearedBalance: Int = 0,
                @Transient
            var isVisiable:Boolean = false
            )

            @JsonClass(generateAdapter = true)
            data class CurrencyFormat(
                @Json(name = "currency_symbol")
                val currencySymbol: String = "",
                @Json(name = "decimal_digits")
                val decimalDigits: Int = 0,
                @Json(name = "decimal_separator")
                val decimalSeparator: String = "",
                @Json(name = "display_symbol")
                val displaySymbol: Boolean = false,
                @Json(name = "example_format")
                val exampleFormat: String = "",
                @Json(name = "group_separator")
                val groupSeparator: String = "",
                @Json(name = "iso_code")
                val isoCode: String = "",
                @Json(name = "symbol_first")
                val symbolFirst: Boolean = false
            )

            @JsonClass(generateAdapter = true)
            data class DateFormat(
                @Json(name = "format")
                val format: String = ""
            )
        }
    }
}