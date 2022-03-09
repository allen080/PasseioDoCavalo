/*
Passeio do Cavalo
Alunos: Luanderlandy F. da Silva e Gabriel Duque

*/

import java.util.Scanner; // main principal

public class PasseioDoCavalo {

	public static void tentarVencer(){ // roda o programa 1000 vezes para tentar resolver o problema
		Cavalo n_cavalo;
		int tentativas[] = new int[1000]; // vetor com todas as tentarivas
		int rand,cont,maior,menor,media = 0;

		System.out.println("\n[*] Executando 1000 vezes...");

		for(int i=0;i<1000;i++){
			n_cavalo = new Cavalo(); // instancia um cavalo a cada rodada das 1000
			cont = 0; // zera o contador

			for(int j=0;j<64;j++) { // executa ate no maximo 64 vezes
				n_cavalo.checarPosicoes(); // checa posicoes disponiveis
				
				rand = (int)( (Math.random()*n_cavalo.getDisponiveis()) + 97); // gera um inteiro entr 'a' e 'h'
			
				if(n_cavalo.moverCavalo(rand))
					cont++; // incrementa o contador se o cavalo foi movido
				else{
					tentativas[i] = cont; // armazena o total de tentativas
					break;
				}
			}
		}

		maior = menor = tentativas[0];
		for(int i: tentativas){
			if(i>maior) maior = i; // armazena o maior valor entre tds as tentativas
			if(i<menor) menor = i; // armazena o menor valor entre tds as tentativas
			media += i; // incrementa a media
		}
		media /= 1000;
		
		System.out.printf("[*] Finalizado as 1000 tentativas\n\nmaior contagem de movimentos: %d\nmenor contagem de movimentos: %d\nmedia entre todos: %d\n\n",maior,menor,media);
	}

	public static void main(String args[]) {
		Cavalo cavalo = new Cavalo(); // objeto cavalo
		Scanner stdin = new Scanner(System.in); // entrada padrao
		char pos; // posicao escolhida para mover
		int op; // opcao de jogo

		System.out.print("1) Jogar\n2) Executar 1000 vezes\n3) Sair\n\nDigite uma opcao: ");
		op = stdin.nextInt();

		if(op<1 || op>3){
			System.out.println("[!] invalido");
			return;
		}

		if(op == 2)
			tentarVencer(); // executa as 1000 vezes
		else if(op == 3)
			System.out.println("[*] Encerrando"); // sai do jogo
		else { // joga
			System.out.println("\nK = posicao atual do cavalo\nx = posicoes ja ocupadas\n'a' a 'h': posicoes disponiveis para mover\n");

			while(true) {	
				cavalo.exibirTabuleiro(cavalo.checarPosicoes());

				if(cavalo.checarPerdeu()){
					System.out.println("[!] Nao ha mais posicoes livres\nTente novamente!");
					System.out.println("Total de movimentos: "+cavalo.getContPos());
					break;
				}

				System.out.print("Posicao desejada (entre 'a' e no maximo 'h'): ");
				pos = stdin.next().charAt(0);

				if(pos == 0) break;

				if(cavalo.moverCavalo((int)pos)) // move o cavalo para posicao informada se for possivel
					System.out.printf("\n[*] Cavalo movido\nContador de passos: %d\n",cavalo.getContPos());
				else{
					System.out.println("[!] Nao ha mais posicoes livres\nTente novamente!");
					System.out.println("Total de movimentos: "+cavalo.getContPos());
					break;
				}

				cavalo.exibirTabuleiro(); // exibe o tabuleiro completo
				System.out.println();
			}
		}
		stdin.close();
	}
}