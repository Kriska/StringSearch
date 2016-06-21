package krissy.fmi.string.search.project;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
	final static Path PATH_EXAMPLE = new File("D:\\FMIJAVA").toPath();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Type searched string: ");
		String searchString = sc.nextLine();
		//System.out.println("Type path: ");
		//Path startPath = new File(sc.nextLine()).toPath();
		Path startPath = PATH_EXAMPLE;
		Searcher s = new Searcher(startPath, searchString);
		s.perform(2,1);
	}
}