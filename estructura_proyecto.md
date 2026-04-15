# Estructura de carpetas y archivos Java del proyecto

## src/main/java/proyect/template

- TemplateApplication.java
- application/
  - WidgetServiceUseCase.java
- domain/
  - entity/
    - Widget.java
  - exception/
    - WidgetConflictProblem.java
    - WidgetNotFoundProblem.java
  - ports/
    - WidgetPortIn.java
    - WidgetPortOut.java
- infrastructure/
  - controller/
    - WidgetController.java
  - dto/
    - WidgetRequestDto.java
    - WidgetResponseDto.java
  - exception/
    - DatabaseException.java
  - mapper/
    - WidgetMapperDto.java
  - repository/
    - entity/
      - WidgetData.java
    - mapper/
      - WidgetEntityMapper.java
    - repository/
      - WidgetJpaRepository.java
      - WidgetRepository.java

## src/test/java/proyect/template

- TemplateApplicationTests.java
- application/
  - WidgetServiceUseCaseTest.java
- infrastructure/
  - controller/
    - WidgetControllerTest.java
  - mapper/
    - WidgetMapperDtoTest.java
  - repository/
    - mapper/
      - WidgetEntityMapperTest.java

