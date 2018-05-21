package main;

import core.problem.State;
import core.solver.Solver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;


	public class MainApp extends Application {

		private static Solver solver;
		private static State state;

		@Override
		public void start(Stage primaryStage) {
			try {
				TabPane root;
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("/view/mainView/MainView.fxml"));
				root = (TabPane) loader.load();
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("n-queens problem");
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}
		
		public static void solve() {
			setState(solver.solve(state));
		}
		
		public static Solver getSolver() {
			return solver;
		}

		public static void setSolver(Solver solver) {
			MainApp.solver = solver;
		}

		public static void setState(State state) {
			MainApp.state = state;
		}
		
		public static State getState() {
			return state;
		}


}
