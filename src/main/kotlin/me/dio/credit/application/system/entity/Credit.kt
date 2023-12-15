package me.dio.credit.application.system.entity

import jakarta.persistence.*
import me.dio.credit.application.system.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Entity //Vai se tornar uma tabela no db
@Table(name = "Credito") //Posso alterar o nome da tablea
data class Credit (
    @Column(nullable = false, unique = true) val creditCode: UUID = UUID.randomUUID(), //vai gerar automaticamente
    @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFirstInstallment: LocalDate,
    @Column(nullable = false) val numberOfInstallments: Int = 0,
    @Enumerated val status: Status = Status.IN_PROGRESS,
    @ManyToOne val customer: Customer?,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
