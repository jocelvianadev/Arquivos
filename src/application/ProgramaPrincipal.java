/*
 Fazer um programa para ler o caminho de um arquivo .csv
contendo os dados de itens vendidos. Cada item possui um
nome, preço unitário e quantidade, separados por vírgula. Você
deve gerar um novo arquivo chamado "summary.csv", localizado
em uma subpasta chamada "out" a partir da pasta original do
arquivo de origem, contendo apenas o nome e o valor total para
aquele item (preço unitário multiplicado pela quantidade),
conforme exemplo.
 */

package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Produto;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		
		List<Produto>lista = new ArrayList<>();
		
		System.out.println("Informe o caminho do arquivo: ");
		String caminho = scan.nextLine();
		
		File procuraArquivo = new File(caminho);
		String procuraPasta = procuraArquivo.getParent();
		
		boolean sucesso = new File(procuraPasta + "\\saida").mkdir();
		
		String alvo = procuraPasta + "\\saida\\sumario.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(procuraArquivo))){
			String itemCsv = br.readLine();
			while(itemCsv != null) {
				String[] campos = itemCsv.split(", ");
				itemCsv = br.readLine();
				String nome = campos[0];
				double preco = Double.parseDouble(campos[1]);
				int quantidade = Integer.parseInt(campos[2]);
				
				lista.add(new Produto(nome, preco, quantidade));
				
				itemCsv = br.readLine();
			}
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(alvo))){
				for(Produto item : lista) {
					bw.write(item.getNome() + "," + String.format("%.2f",item.total()));
					bw.newLine();
				}
				System.out.println(alvo + " Criado!");
			}
			catch (IOException e) {
				System.out.println("Erro na escrita do arquivo: " + e.getMessage());
			}
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
						
		scan.close();
	}

}
