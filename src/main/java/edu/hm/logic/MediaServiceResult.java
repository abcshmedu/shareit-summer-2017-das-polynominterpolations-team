package edu.hm.logic;

/** Dies ist unsere Implementierung des MediaServiceResult-Enums.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public enum MediaServiceResult {
    OK(200), FAIL(300);

    /** Diese Variable enthält den Error-Code jedes
     * MediaServiceResult-Objekts. */
    private int statusCode;

    /** Diese Variable enthält zusätzliche Informationen bezüglich des
     * Error-Codes. */
    private String detail;

    /** Default Ctor des MediaServiceResult-Enums.
     * 
     * @param errorCode Der Error-Code, des Enums */
    MediaServiceResult(int errorCode) {
        this.statusCode = errorCode;
        this.detail = "";
    }

    /** Diese Methode ermöglicht es dem Enum eine Error-Message mitzugeben.
     * 
     * @param message Die Nachricht des Enums*/
    public void setDetail(String message) {
        this.detail = message;
    }

    /** Getter für den Error-Code.
     * 
     * @return Liefert den Error-Code zurück */
    public int getStatus() {
        return statusCode;
    }

    /** Getter für die Error-Message.
     * 
     * @return Liefert die Error-Message zurück */
    public String getDetail() {
        return detail;
    }
}
