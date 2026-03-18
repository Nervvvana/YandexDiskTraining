Версия alpha-0.2

Реализованы тесты на все группы методов API

Имеется несколько (3-4) flaky-тестов


P.S.
Почему падает тест `SaveResourceTest.Negative.savePublicResourceWithInvalidPublicKey`, понять не могу:
по факту в тесте прописано 404, а в отчетах пишет что тест ожидает 202/204 статус
