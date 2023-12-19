package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Valor nao pode ser nulo") val creditValue: BigDecimal,
    @field:Future val dayFirstOfInstallment: LocalDate,
    @field:NotNull(message = "Valor nao pode ser nulo")
        @field:Future(message = "Precisa ser uma data no futuro")
            @field:Max(value = 48, message = "Número de parcelas deve ser no máximo 48")
                val numberOfInstallments: Int,
    @field:NotNull(message = "Valor nao pode ser nulo") val customerId: Long

) {
    init {
        validateDates()
    }

    private fun validateDates() {
        val currentDate = LocalDate.now()
        val maxDate = currentDate.plusMonths(3)

        if (dayFirstOfInstallment.isBefore(currentDate) || dayFirstOfInstallment.isAfter(maxDate)) {
            throw IllegalArgumentException("A data da primeira parcela deve ser no máximo 3 meses após o dia atual")
        }
    }

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
