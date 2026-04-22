import kotlin.system.exitProcess

class VirtualPet(val nome: String) {
    var nivelDeFome = 50
    var nivelFelicidade = 50
    var nivelCansaço = 0
    var idade = 0
    var vontadeBanheiro = 0
    var nivelSujeira = 0

    fun alimentar() {
        nivelDeFome = (nivelDeFome - 10).coerceAtLeast(0)
        vontadeBanheiro += 25
        println("$nome foi alimentado. A fome diminuiu, mas a vontade de ir ao banheiro aumentou!")
    }

    fun descansar() {
        println("Por quantas horas $nome vai descansar? (1h até 8h)")
        val tempoDescanso = readLine()?.toIntOrNull()

        if (tempoDescanso != null && tempoDescanso in 1..8) {
            if (tempoDescanso == 8) {
                nivelCansaço = 0
                println("$nome dormiu profundamente por 8 horas e está renovado!")
            } else {
                val recuperacao = tempoDescanso * 13
                nivelCansaço = (nivelCansaço - recuperacao).coerceAtLeast(0)
                println("$nome descansou por $tempoDescanso horas.")
            }
        } else {
            println("Valor inválido! Digite um número de 1 a 8.")
        }
    }

    fun brincar() {
        nivelFelicidade = (nivelFelicidade + 15).coerceAtMost(100)
        nivelCansaço += 15
        nivelSujeira += 20
        println("$nome se divertiu muito, mas se sujou um pouco!")
    }
    fun darBanho() {
        nivelSujeira = 0
        println("$nome tomou um banho caprichado e está limpinho!")
    }

    fun usarBanheiro() {
        vontadeBanheiro = 0
        println("$nome usou o banheiro e está aliviado!")
    }

    fun verificarStatus() {
        println("Status atual de $nome:")
        println("Nível de fome: $nivelDeFome")
        println("Nível de felicidade: $nivelFelicidade")
        if(idade==50){
        println("Parabéns! $nome completou 50 anos! Você atingiu o objetivo!")
        }else
        println("Idade atual: $idade anos")
    }

    fun passarTempo() {
        nivelDeFome += 3
        idade += 1
        nivelFelicidade -= 3
        nivelCansaço += 10
        println("$nome está ficando mais faminto com o passar do tempo.")
    }

    fun estaVivo(): Boolean {
        if (nivelDeFome >= 100) {
            println("\n💀 Fim de jogo: $nome morreu de fome!")
            return false
        }
        if (nivelCansaço >= 100) {
            println("\n💀 Fim de jogo: $nome morreu de exaustão!")
            return false
        }
        if (nivelFelicidade <= 0) {
            println("\n💀 Fim de jogo: $nome fugiu de casa por estar muito triste!")
            return false
        }
        if (vontadeBanheiro >= 100) {
            println("\n💩 Oh não! $nome não aguentou e houve um acidente desastroso. Fim de jogo!")
            return false
        }
        if (nivelSujeira >= 100) {
            println("\n🤢 Fim de jogo: $nome ficou doente de tanta sujeira e foi para o veterinário!")
            return false
        }
        return true
    }
}

fun main() {
    println("Bem-vindo ao Simulador de Animal de Estimação Virtual!")
    println("Digite o nome do seu animal de estimação:")
    val nomePet = readLine() ?: "Baltazar Guilherme Tenório"
    val pet = VirtualPet(nomePet)

    while (true) {
        println("\nEscolha uma ação:")
        println("1. Alimentar $nomePet")
        println("2. Brincar com $nomePet")
        println("3. Verificar o status de $nomePet")
        println("4. Descansar $nomePet")
        println("5. Dar banho em $nomePet")
        println("6. Levar $nomePet ao banheiro")
        println("7. Sair")

        val escolha = readLine()?.toIntOrNull() ?: continue

        when (escolha) {
            1 -> pet.alimentar()
            2 -> pet.brincar()
            3 -> pet.verificarStatus()
            4 -> pet.descansar()
            5 -> pet.darBanho()
            6 -> pet.usarBanheiro()
            7 -> {
                println("Saindo do Simulador de Animal de Estimação Virtual. Adeus!")
                return
            }

            else -> println("Escolha inválida. Tente novamente.")
        }

        pet.passarTempo() // Simula o tempo que passa após cada ação

        if (!pet.estaVivo()) {
            println("--- GAME OVER ---")
            break
        }
    }
}