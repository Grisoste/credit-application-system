package me.dio.credit.application.system.entity

import jakarta.persistence.*

@Entity //Vai se tornar uma tabela no db
@Table(name = "Cliente") //Posso alterar o nome da tablea
data class Customer(
    @Column(nullable = false)
        var firstName: String = "", //Nao pode ser nulo
    @Column(nullable = false)
        var lastName: String = "",
    @Column(nullable = false, unique = true)
        val cpf: String, //dizendo que o email so pode ser unico
    @Column(nullable = false, unique = true)
        var email: String = "",
    @Column(nullable = false)
        var password: String = "",
    @Column(nullable = false) @Embedded
        var adress: Address = Address(),
    @Column(nullable = false) @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.REMOVE), mappedBy = "customer")
        var credits: List<Credit> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null
    //@Id to querendo dizer que é a chave primaria
    //@GeneratedValue é pra gerar os valores
)
