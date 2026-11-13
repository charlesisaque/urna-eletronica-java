================================================================================

URNA CIDADÃ — SIMULADOR DE URNA ELETRÔNICA

================================================================================



Simulador educacional de urna eletrônica desenvolvido em Java com interface 

gráfica Java Swing e persistência de dados em nuvem via Firebase (Cloud Firestore).



Este projeto foi desenvolvido como parte de uma atividade de extensão 

universitária da UNIJORGE, com foco em cidadania, educação política e 

conscientização contra a desinformação para estudantes do ensino secundário.



--------------------------------------------------------------------------------

1. TECNOLOGIAS UTILIZADAS

--------------------------------------------------------------------------------

- Linguagem: Java (JDK 17 / 21)

- Interface Gráfica: Java Swing

- Gerenciamento de Dependências / Build: Maven

- Banco de Dados / Nuvem: Firebase Admin SDK & Cloud Firestore

- Arquitetura: Padrão em camadas baseado em Programação Orientada a Objetos (POO)



--------------------------------------------------------------------------------

2. ARQUITETURA DO PROJETO

--------------------------------------------------------------------------------

O projeto segue estritamente os princípios da POO (Herança, Polimorfismo, 

Encapsulamento e Abstração) e está organizado em pacotes dentro de 

src/main/java/br/edu/unijorge/urna/:



urna-cidada/

├── pom.xml

└── src/

    └── main/

        └── java/

            └── br/

                └── edu/

                    └── unijorge/

                        └── urna/

                            ├── model/         (Classes de Domínio)

                            ├── service/       (Comunicação com Firestore)

                            ├── controller/    (Regra de negócio e fluxo)

                            └── view/          (Interface Gráfica Swing)



--------------------------------------------------------------------------------

3. REGRAS DE NEGÓCIO E FLUXO DE VOTAÇÃO

--------------------------------------------------------------------------------

A urna simula o processo de votação oficial brasileiro adaptado para dois cargos:

1. Vereador (Cargo Proporcional): Digitação de 5 dígitos.

2. Prefeito (Cargo Majoritário): Digitação de 2 dígitos.



CANDIDATOS FICTÍCIOS CADASTRADOS PARA TESTE:



* Vereadores (5 dígitos):

  - 10101 : Ada Lovelace (Partido da Programação - PPROG)

  - 20202 : Alan Turing (Partido da Lógica - PLOG)

  - 30303 : Grace Hopper (Partido dos Compiladores - PCOMP)



* Prefeitos (2 dígitos):

  - 10 : Linus Torvalds (Partido do Código Aberto - PCA)

  - 20 : Margaret Hamilton (Partido da Engenharia - PENG)



* Opções Especiais:

  - BRANCO : Botão Branco na interface.

  - NULO   : Qualquer número diferente dos cadastrados.



--------------------------------------------------------------------------------

4. COMO EXECUTAR O PROJETO

--------------------------------------------------------------------------------

PRÉ-REQUISITOS:

- Ter o Java JDK instalado (versão 17 ou superior).

- Ter o IntelliJ IDEA (ou outra IDE Java) com suporte ao Maven.

- Uma conta ativa no Firebase com um projeto configurado e o Cloud Firestore 

  ativado no modo de teste.



PASSO A PASSO:

1. Clone o repositório ou abra a pasta no IntelliJ:

   git clone https://github.com/seu-usuario/urna-cidada.git



2. Configure o Firebase:

   - Acesse o console do Firebase, vá em Configurações do Projeto > Contas de Serviço.

   - Clique em "Gerar nova chave privada" e salve o arquivo baixado com o nome 

     exato de: serviceAccountKey.json

   - Coloque esse arquivo na raiz do projeto (urna-cidada/serviceAccountKey.json).

   - Nota: Por segurança, este arquivo não deve ser enviado ao GitHub público.



3. Sincronize as dependências do Maven:

   - Abra o arquivo pom.xml no IntelliJ e clique no ícone de atualização do 

     Maven (Reload All Maven Projects).



4. Execute a aplicação:

   - Navegue até o pacote view, abra a classe UrnaView.java.

   - Clique com o botão direito e selecione "Run 'UrnaView.main()'".



--------------------------------------------------------------------------------

5. AUTORIA

--------------------------------------------------------------------------------

Projeto acadêmico desenvolvido para fins educacionais na UNIJORGE. É noisss
