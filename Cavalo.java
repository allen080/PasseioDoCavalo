public class Cavalo { // Classe cavalo
	private final int horizontal[] = {2,1,-1,-2,-2,-1,1,2};
	private final int vertical[] = {-1,-2,-2,-1,1,2,2,1};
	private int tabuleiro[][];
	private int linhaAtual,colunaAtual,contPosicoes,contDisponiveis;
	private boolean checarPerdeu;

	public Cavalo() { // construtor
		tabuleiro = new int[8][8];
		contPosicoes = 0;
		contDisponiveis = 0;
		checarPerdeu = false;

		linhaAtual = 3; // posicao inicial do centro
		colunaAtual = 4;

		for(int i=0;i<tabuleiro.length;i++) {
			for(int j=0;j<tabuleiro[0].length;j++) {
				if(i==linhaAtual && j==colunaAtual)
					tabuleiro[i][j] = 'K';
				else
					tabuleiro[i][j] = 0;
			}
		}
	}

	public int getContPos(){ // contador geral de movimentos
		return this.contPosicoes;
	}
	public int getDisponiveis(){ // posicoes disponiveis a cada jogada
		return this.contDisponiveis;
	}
	public boolean checarPerdeu(){ // checar se ha posicoes disponiveis
		return this.checarPerdeu;
	}

	public void exibirTabuleiro(int[][] tabuleiro) { // exibe o tabuleiro informado
		System.out.println();

		for(int[] pos1: tabuleiro) {
			for(int pos2: pos1) {
				System.out.printf("|%c",pos2);
			}
			System.out.println("|");
		}
	}
	public void exibirTabuleiro() { // exibe o tabuleiro padrao
		System.out.println();

		for(int[] pos1: tabuleiro) {
			for(int pos2: pos1) {
				System.out.printf("|%c",pos2);
			}
			System.out.println("|");
		}
	}

	public void ocuparPosicao(int new_x,int new_y,int old_x,int old_y) { // ocupa a posicao do cavalo e adiciona o 'x' na posicao antiga
		for(int i=0; i<this.tabuleiro.length; i++) {
			for(int j=0; j<this.tabuleiro[0].length; j++) {
				if(i==old_x && j==old_y && this.tabuleiro[i][j] != 'x')
					this.tabuleiro[i][j] = 'x';
				else if(i==new_x && j==new_y && this.tabuleiro[i][j]!='K')
					this.tabuleiro[i][j] = 'K';
			}
		}
	}

	public int[][] copiarTabuleiro(){ // copia o tabuleiro removendo os 'x'
		int tab[][] = new int[8][8];

		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(tabuleiro[i][j] == 'x')
					tab[i][j] = ' ';
				else
					tab[i][j] = this.tabuleiro[i][j];
			}
		}
		return tab;
	}

	private boolean isValid(int num){ // verifica se o numero esta no intervalo especifico 1 e 7
		return num > 0 && num <= 7;
	}

	public int[][] checarPosicoes(){ // checa posicoes disponiveis
		char posicoes[] = {'a','b','c','d','e','f','g','h'};
		int temp_tabuleiro[][] = copiarTabuleiro();
		int cont_pos = 0;
		int h,v;

		for(int i=0;i<this.horizontal.length;i++){ // percorre todo tabuleiro e 
			h = this.horizontal[i]; v = this.vertical[i];
			if( isValid(linhaAtual+h) && isValid(colunaAtual+v) && this.tabuleiro[linhaAtual+h][colunaAtual+v] != 'x')
				temp_tabuleiro[this.linhaAtual+h][this.colunaAtual+v] = posicoes[cont_pos++];
		}

		if(cont_pos==0) // se nao achou nenhuma posicao, o jogador perdeu
			this.checarPerdeu = true;		
		else
			this.contDisponiveis = cont_pos;

		return temp_tabuleiro;
	}

	public boolean moverCavalo(int pos){ // move o cavalo entre 'a' e 'h'
		int tab[][] = checarPosicoes(); 
		boolean achou = false;

		for(int i=0;i<tab.length;i++){
			for(int j=0;j<tab[i].length;j++){
				if(tab[i][j] == pos){	
					achou = true;
					// informa que foi achado um valor e adiciona as informacoes

					ocuparPosicao(i,j,this.linhaAtual,this.colunaAtual);
					this.linhaAtual = i;
					this.colunaAtual = j;
					this.contPosicoes++;
					break;
				}
			}
		}
		return achou;
	}
}// fim da classe