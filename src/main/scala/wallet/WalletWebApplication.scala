package main.scala.wallet
import org.springframework.boot.SpringApplication
import wallet.WalletConfig

object WalletWebApplication {
	def main(args: Array[String]) : Unit = {
			SpringApplication.run(classOf[WalletConfig])
	}
}