# Gemini Usage for Jimjam Development

## UI Component Generation
- Designed and generated custom layout for a warm chatbot interface.

## Architectural Refactoring
- Analyzed current package structures to identify "identity crises" in classes like `Ui` and `Command`.
- Applied Separation of Concerns by decoupling business logic from presentation logic.
- Restructured the project by moving logic-heavy classes (`Ui` and `Command`) out of the JavaFX-specific `ui` package.

## User Guide
- Used ChatGPT and Gemini to generate User Guide using Address Book 3 and [AK-matrix](https://github.com/AK-matrix/ip/tree/master/docs)'s user guide as inspiration.
