import re


class InvalidEmailError(ValueError):
    pass


class UnderageError(Exception):
    pass


class RegistrationService:

    def register_user(self, email: str, age: int) -> bool:

        assert email is not None, "Email cannot be None"

        if email.strip() == "":
            raise InvalidEmailError("Email cannot be empty")

        pattern = r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]+$"

        if not re.match(pattern, email):
            raise InvalidEmailError(f"Invalid email format: {email}")

        if age < 18:
            raise UnderageError(f"User age {age} is below 18")

        return True
