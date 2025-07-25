/**
 * 
 */
package com.javateam.SpringBootJPAEntities.exception;

/**
 * 재고 부족 예외
 * @author javateam
 *
 */
@SuppressWarnings("serial")
public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException() {
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }

}
