package wanderers.ai.admin_portal.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("error/404");
        mav.addObject("status", 404);
        mav.addObject("error", "Not Found");
        mav.addObject("message", ex.getMessage());
        mav.addObject("path", req.getRequestURI());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGeneric(Exception ex, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("error/500");
        mav.addObject("status", 500);
        mav.addObject("error", "Internal Server Error");
        mav.addObject("message", "Something went wrong. Please try again.");
        mav.addObject("path", req.getRequestURI());
        return mav;
    }
}

