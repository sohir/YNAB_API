package com.task.ynab.models


import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetDetailsResponseModel(
    @Json(name = "data")
    val `data`: Data = Data()
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "budget")
        val budget: Budget = Budget(),
        @Json(name = "server_knowledge")
        val serverKnowledge: Int = 0
    ) {
        @JsonClass(generateAdapter = true)
        data class Budget(
            @Json(name = "accounts")
            val accounts: List<Account> = listOf(),
            @Json(name = "categories")
            val categories: List<Category> = listOf(),
            @Json(name = "category_groups")
            val categoryGroups: List<CategoryGroup> = listOf(),
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
            @Json(name = "months")
            val months: List<Month> = listOf(),
            @Json(name = "name")
            val name: String = "",
            @Json(name = "payee_locations")
            val payeeLocations: List<Any> = listOf(),
            @Json(name = "payees")
            val payees: List<Payee> = listOf(),
            @Json(name = "scheduled_subtransactions")
            val scheduledSubtransactions: List<Any> = listOf(),
            @Json(name = "scheduled_transactions")
            val scheduledTransactions: List<Any> = listOf(),
            @Json(name = "subtransactions")
            val subtransactions: List<Any> = listOf(),
            @Json(name = "transactions")
            val transactions: List<Transaction> = listOf()
        ) {
            @JsonClass(generateAdapter = true)
            data class Account(
                @Json(name = "balance")
                val balance: Int = 0,
                @Json(name = "cleared_balance")
                val clearedBalance: Int = 0,
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
                val unclearedBalance: Int = 0
            )

            @JsonClass(generateAdapter = true)
            data class Category(
                @Json(name = "activity")
                val activity: Int = 0,
                @Json(name = "balance")
                val balance: Int = 0,
                @Json(name = "budgeted")
                val budgeted: Int = 0,
                @Json(name = "category_group_id")
                val categoryGroupId: String = "",
                @Json(name = "deleted")
                val deleted: Boolean = false,
                @Json(name = "goal_creation_month")
                val goalCreationMonth: Any? = null,
                @Json(name = "goal_percentage_complete")
                val goalPercentageComplete: Any? = null,
                @Json(name = "goal_target")
                val goalTarget: Int = 0,
                @Json(name = "goal_target_month")
                val goalTargetMonth: Any? = null,
                @Json(name = "goal_type")
                val goalType: Any? = null,
                @Json(name = "hidden")
                val hidden: Boolean = false,
                @Json(name = "id")
                val id: String = "",
                @Json(name = "name")
                val name: String = "",
                @Json(name = "note")
                val note: Any? = null,
                @Json(name = "original_category_group_id")
                val originalCategoryGroupId: Any? = null
            )

            @JsonClass(generateAdapter = true)
            data class CategoryGroup(
                @Json(name = "deleted")
                val deleted: Boolean = false,
                @Json(name = "hidden")
                val hidden: Boolean = false,
                @Json(name = "id")
                val id: String = "",
                @Json(name = "name")
                val name: String = "",
                @Transient
                var itsCategoriesList:  ArrayList<Category> =  arrayListOf<Category>(),
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

            @JsonClass(generateAdapter = true)
            data class Month(
                @Json(name = "activity")
                val activity: Int = 0,
                @Json(name = "age_of_money")
                val ageOfMoney: Any? = null,
                @Json(name = "budgeted")
                val budgeted: Int = 0,
                @Json(name = "categories")
                val categories: List<Category> = listOf(),
                @Json(name = "deleted")
                val deleted: Boolean = false,
                @Json(name = "income")
                val income: Int = 0,
                @Json(name = "month")
                val month: String = "",
                @Json(name = "note")
                val note: Any? = null,
                @Json(name = "to_be_budgeted")
                val toBeBudgeted: Int = 0
            ) {
                @JsonClass(generateAdapter = true)
                data class Category(
                    @Json(name = "activity")
                    val activity: Int = 0,
                    @Json(name = "balance")
                    val balance: Int = 0,
                    @Json(name = "budgeted")
                    val budgeted: Int = 0,
                    @Json(name = "category_group_id")
                    val categoryGroupId: String = "",
                    @Json(name = "deleted")
                    val deleted: Boolean = false,
                    @Json(name = "goal_creation_month")
                    val goalCreationMonth: Any? = null,
                    @Json(name = "goal_percentage_complete")
                    val goalPercentageComplete: Any? = null,
                    @Json(name = "goal_target")
                    val goalTarget: Int = 0,
                    @Json(name = "goal_target_month")
                    val goalTargetMonth: Any? = null,
                    @Json(name = "goal_type")
                    val goalType: Any? = null,
                    @Json(name = "hidden")
                    val hidden: Boolean = false,
                    @Json(name = "id")
                    val id: String = "",
                    @Json(name = "name")
                    val name: String = "",
                    @Json(name = "note")
                    val note: Any? = null,
                    @Json(name = "original_category_group_id")
                    val originalCategoryGroupId: Any? = null
                )
            }

            @JsonClass(generateAdapter = true)
            data class Payee(
                @Json(name = "deleted")
                val deleted: Boolean = false,
                @Json(name = "id")
                val id: String = "",
                @Json(name = "name")
                val name: String = "",
                @Json(name = "transfer_account_id")
                val transferAccountId: Any? = null
            )

            @JsonClass(generateAdapter = true)
            data class Transaction(
                @Json(name = "account_id")
                val accountId: String = "",
                @Json(name = "amount")
                val amount: Int = 0,
                @Json(name = "approved")
                val approved: Boolean = false,
                @Json(name = "category_id")
                val categoryId: String = "",
                @Json(name = "cleared")
                val cleared: String = "",
                @Json(name = "date")
                val date: String = "",
                @Json(name = "deleted")
                val deleted: Boolean = false,
                @Json(name = "flag_color")
                val flagColor: Any? = null,
                @Json(name = "id")
                val id: String = "",
                @Json(name = "import_id")
                val importId: Any? = null,
                @Json(name = "matched_transaction_id")
                val matchedTransactionId: Any? = null,
                @Json(name = "memo")
                val memo: Any? = null,
                @Json(name = "payee_id")
                val payeeId: String = "",
                @Json(name = "transfer_account_id")
                val transferAccountId: Any? = null,
                @Json(name = "transfer_transaction_id")
                val transferTransactionId: Any? = null
            )
        }
    }
}