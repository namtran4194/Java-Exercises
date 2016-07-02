package adapters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Chỉ nhập được số có 1 chữ số trong JTextField.
 * Sử dụng JTextField.setDocument(new DigitsDocument(int limit));
 * dùng trong class Sudoku.
 * */
public class DigitsDocument extends PlainDocument {
	private static final long serialVersionUID = 1L;
	int limit; // Giới hạn nhập bao nhiêu kí tự

	public DigitsDocument(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (str == null) {
			return;
		}
		char c = str.charAt(0);
		if (c >= '0' && c <= '9')
			if (getLength() + str.length() <= limit) {
				super.insertString(offs, str, a);
			}
	}
}
