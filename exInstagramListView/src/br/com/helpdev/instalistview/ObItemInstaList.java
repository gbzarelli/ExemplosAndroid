package br.com.helpdev.instalistview;

public class ObItemInstaList {
	private String title, content;

	public ObItemInstaList() {
		this("", "");
	}

	public ObItemInstaList(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ObItemInstaList [title=" + title + ", content=" + content + "]";
	}

}
