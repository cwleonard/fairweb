package com.amphibian.fair.beans;

public class GeneralInformationItem {

	private final static int SHORT_LENGTH = 200;
	
	private final static int NOT_AS_SHORT_LENGTH = 500;
	
	private int id;
	
	private String text;

	private String headline;
	
	private String image;
	
	/**
	 * Standard Constructor.
	 */
	public GeneralInformationItem() {
		id = -1;
		text = null;
		headline = null;
		image = null;
	}
	
	/**
	 * @return the headline
	 */
	public String getHeadline() {
		return headline;
	}

	/**
	 * @param headline the headline to set
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	public String getShortText() {
		
		int cutoff = (image == null ? SHORT_LENGTH : NOT_AS_SHORT_LENGTH);
		
		if (text != null) {
			if (text.indexOf('<') == -1) {
				if (text.length() > cutoff) {
					return text.substring(0, cutoff) + "...";
				} else {
					return text;
				}
			} else {
				return text.substring(0, text.indexOf('<')) + "...";
			}
		} else {
			return null;
		}
		
	}
	
	public boolean isTextLengthEqual() {
		
		return (getText().length() == getShortText().length());
		
	}
	
	public String toString() {
		return headline;
	}
	
	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<GeneralInformationItem id=\"");
		buf.append(id);
		buf.append("\">");
		buf.append("<Headline>");
		if (headline != null) {
			buf.append(headline.replaceAll("&", "&amp;"));
		}
		buf.append("</Headline>");
		buf.append("<Text>");
		if (text != null) {
			buf.append(text.replaceAll("&", "&amp;"));
		}
		buf.append("</Text>");
		buf.append("<ShortText>");
		if (text != null) {
			buf.append(this.getShortText().replaceAll("&", "&amp;"));
		}
		buf.append("</ShortText>");
		buf.append("<Image>");
		if (image != null) {
			buf.append(image);
		}
		buf.append("</Image>");
		buf.append("<DisplayText>");
		buf.append(toString().replaceAll("&", "&amp;"));
		buf.append("</DisplayText>");
		buf.append("</GeneralInformationItem>");
		
		return buf.toString();
		
	}
	
}
