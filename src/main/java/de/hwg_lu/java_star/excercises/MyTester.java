package de.hwg_lu.java_star.excercises;

class MyTester {
	class IntMatrix {
		int numberOfRows = 0;
		int numberOfColumns = 0;
		int[][] matrix = null;

		IntMatrix(int rows, int columns) {
			this.numberOfRows = rows;
			this.numberOfColumns = columns;
			this.matrix = new int[rows][columns];
		}

		IntMatrix add(IntMatrix other) {
			if (this.numberOfRows != other.numberOfRows || this.numberOfColumns != other.numberOfColumns) {
				throw new ArithmeticException("wrong dimension by adding matrix");
			}
			IntMatrix sum = new IntMatrix(this.numberOfRows, this.numberOfColumns);
			for (int i = 0; i < this.numberOfRows; ++i) {
				for (int j = 0; j < this.numberOfColumns; ++j) {
					sum.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
				}
			}
			return sum;
		}

		IntMatrix multiply(IntMatrix other) {
			if (this.numberOfColumns != other.numberOfRows) {
				throw new ArithmeticException("wrong dimension by multiplying matrix");
			}
			IntMatrix product = new IntMatrix(this.numberOfRows, other.numberOfColumns);
			for (int i = 0; i < this.numberOfRows; ++i) {
				for (int j = 0; j < this.numberOfColumns; ++j) {
					product.matrix[i][j] = 0;
					for (int k = 0; k < this.numberOfColumns; ++k) {
						product.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
					}
				}
			}
			return product;
		}

		void set(int row, int column, int value) {
			if (row > this.numberOfRows || column > this.numberOfColumns) {
				throw new ArrayIndexOutOfBoundsException(
						"Matrix is " + this.numberOfRows + " x " + this.numberOfColumns);
			}
			this.matrix[row][column] = value;
		}

		int get(int row, int column) {
			if (row > this.numberOfRows || column > this.numberOfColumns) {
				throw new ArrayIndexOutOfBoundsException(
						"Matrix is " + this.numberOfRows + " x " + this.numberOfColumns);
			}
			return this.matrix[row][column];
		}
	}

	public static void main(String[] args) {
		String output = "OK";
		IntMatrix m1 = new MyTester().new IntMatrix(3, 3);
		m1.set(0, 0, 1);
		m1.set(0, 1, 0);
		m1.set(0, 2, 0);
		m1.set(1, 0, 0);
		m1.set(1, 1, 1);
		m1.set(1, 2, 0);
		m1.set(2, 0, 0);
		m1.set(2, 1, 0);
		m1.set(2, 2, 1);
		try {
			m1.set(5, 5, 10);
		} catch (ArrayIndexOutOfBoundsException e) {
		} catch (Exception e1) {
			output += "\n* inserting values outside matrix dimension should throw ArrayIndexOutOfBoundsException.";
		}
		IntMatrix m2 = new MyTester().new IntMatrix(2, 2);
		try {
			m1.add(m2);
		} catch (ArithmeticException e) {
		} catch (Exception e1) {
			output += "\n* adding incompatible matrix should throw ArithmeticException.";
		}
		IntMatrix m3 = new MyTester().new IntMatrix(3, 3);
		int[][] values = { { 0, 1, 2 }, { 3, 2, 1 }, { 4, 3, 1 } };
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				m3.set(i, j, values[i][j]);
			}
		}
		int[][] valuesResult = { { 1, 1, 2 }, { 3, 3, 1 }, { 4, 3, 2 } };
		IntMatrix result = m1.add(m3);
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (result.get(i, j) != valuesResult[i][j]) {
					output += "\n* Error adding matrices";
				}
			}
		}
		System.out.println(output);
	}
}