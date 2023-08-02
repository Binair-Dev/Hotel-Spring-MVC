package be.bnair.lovehotel.validation.validators;

import be.bnair.lovehotel.models.forms.UserForm;
import be.bnair.lovehotel.validation.constraints.PasswordMatches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordMatches, UserForm> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserForm userForm, ConstraintValidatorContext context) {
        if (userForm == null) {
            return true; // La validation est considérée comme réussie si l'utilisateur est nul
        }

        String password = userForm.getPassword();
        String passwordConfirm = userForm.getConfirmPassword();

        // Vérifiez si le mot de passe correspond à la confirmation du mot de passe
        return password != null && password.equals(passwordConfirm);
    }
}