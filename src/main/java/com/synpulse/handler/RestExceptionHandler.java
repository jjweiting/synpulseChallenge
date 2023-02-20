// package com.synpulse.handler;

// import java.util.stream.Collectors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.context.support.DefaultMessageSourceResolvable;
// import org.springframework.core.Ordered;
// import org.springframework.core.annotation.Order;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.ServletRequestBindingException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.context.request.WebRequest;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// import com.synpulse.model.ABCResponse;

// @Order(Ordered.HIGHEST_PRECEDENCE)
// @ControllerAdvice
// public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//     private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

//     /**
//      * Handle ServletRequestBindingException. Triggered when a 'required' request
//      * header parameter is missing.
//      *
//      * @param ex      ServletRequestBindingException
//      * @param headers HttpHeaders
//      * @param status  HttpStatus
//      * @param request WebRequest
//      * @return the ResponseEntity object
//      */
//     @Override
//     protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
//             HttpHeaders headers, HttpStatus status, WebRequest request) {
//         String txno = request.getHeader("api_txno");
//         ABCResponse response;
//         if (ex.getMessage().contains("api_txno")) {
//             response = new ABCResponse(txno, 4000, "No Transaction Number");
//         } else if (ex.getMessage().contains("X-SOURCE-ID")) {
//             response = new ABCResponse(txno, 4001, "No Source Id");
//         } else {
//             response = new ABCResponse(txno, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//         }
//         return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
//     }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<Object> globalExceptionHandler(
//             Exception ex,
//             WebRequest request) {
//         LOGGER.error("globalExceptionHandler ex:" + ex);
//         String txno = request.getHeader("api_txno");
//         ABCResponse response = new ABCResponse(txno, 5000, "Internal Server Error");
//         return ResponseEntity.internalServerError().body(response);
//     }
// }