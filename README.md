# sensedia-auth-challenge

O projeto StepsAuthenticator é a biblioteca criada para realizar a autenticação
O projeto cadastro foram APIs criadas para exemplificar o uso da biblioteca

CRIAÇÃO DE SEGREDO

  SecretCodeService secretCodeService = new SecretCodeService();
  SecretInfo secretInfo = secretCodeService.generateSecret();

GERAÇÃO DE CHAVE DE AUTENTICAÇÃO

  SecretInfo secretInfo = SecretInfo.build(user.getSecret());
  secretInfo.setData(user.getUsername());
  AuthenticationKey authenticationKey
          = authenticationKeyService.generateAuthenticationKey(secretInfo);

VALIDAÇÃO DE CHAVE DE AUTENTICAÇÃO

  SecretInfo secretInfo = SecretInfo.build(user.getSecret());
  secretInfo.setData(user.getUsername());
  AuthenticationKey authenticationKey = AuthenticationKey.build(providedAuthenticationKey);
  boolean isValidAuthenticationKey =
          authenticationKeyService
                  .validateAuthenticationKey(secretInfo, authenticationKey);
