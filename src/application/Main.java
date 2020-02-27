package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import entities.Product;

public class Main {
	
	public static void main(String[]args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the Source csv path: ");
		String sourcePath =  sc.nextLine();
		File sourceFile = new File(sourcePath);
		List<Product> products = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourcePath))){
			
			String line = br.readLine();
			while(line != null) {
				
				String aux = line;
				
				String[] productVariables = aux.split(",");
				
				products.add(new Product(productVariables[0],Double.parseDouble(productVariables[1]),Integer.parseInt(productVariables[2])));
					
				line = br.readLine();
			}			
			
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		

		String sourceFolderPath = sourceFile.getParent();
		
		
		boolean success = new File(sourceFolderPath + "\\out").mkdir(); 
		System.out.println("Directory created sucessfully: " + success);
		
		
		
		
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(sourceFolderPath + "\\out\\summary.csv"))){
			
			for(Product product : products) {
				bw.write(product.outputProduct());
				bw.newLine();
			}
			
			System.out.println("Sucess");
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
			
		sc.close();
	}
}


//https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/