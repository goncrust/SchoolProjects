Default account number: 1234
Default PIN: 0000
Default balance: 1000€

#include <stdio.h>
#include <stdbool.h>
#include <locale.h>
#include <string.h>
#include <ctype.h>
#include <io.h>
#include <fcntl.h>

//#pragma execution_character_set("utf-8")

// define menu ids
#define LOGIN 0
#define MAIN 1
#define CHANGE_PIN 2
#define WITHDRAW 3
#define DEPOSIT 4
#define BALANCE 5
#define EXIT 6
#define CONTINUE 7

#define PORTUGUESE 0
#define ENGLISH 1

// account variables
char ACCOUNT[] = "1234\n";
char PIN[] = "0000\n";
bool blocked_account = false;
int balance = 1000;

// menu tracking
int current_menu = LOGIN;
bool running = true;
int language = PORTUGUESE;

// withdraw variables
bool success_withdraw;
int amount_withdraw;
bool last_withdraw = false;

// deposit variables
bool success_deposit;
int amount_deposit = 0;
bool last_deposit = false;

int main()
{
    setlocale(LC_ALL, "");
    //_setmode(_fileno(stdout), _O_U16TEXT);

    mainloop();
}

void mainloop()
{
    while (running)
    {
        current_menu = render_menu(current_menu);
    }
}

int render_menu(menu)
{
    switch (menu)
    {
    case LOGIN:
        return login_menu();
        break;
    case MAIN:
        return main_menu();
        break;
    case CHANGE_PIN:
        return change_pin_menu();
        break;
    case WITHDRAW:
        return withdraw_menu();
        break;
    case DEPOSIT:
        return deposit_menu();
        break;
    case BALANCE:
        return balance_menu();
        break;
    case CONTINUE:
        return continue_menu();
        break;
    case EXIT:
        system("cls");
        return continue_menu();
        break;
    }
}

int login_menu()
{
    int i;
    char pin_entry[10];
    char account_entry[10];

    int current = 1;

    bool first = true;

    while (true)
    {
        system("cls");

        printf("  __  __       _ _   _ _                                    \n");
        printf(" |  \\/  |     | | | (_) |                                  \n");
        printf(" | \\  / |_   _| | |_ _| |__   __ _ _ __   ___ ___          \n");
        printf(" | |\\/| | | | | | __| | '_ \\ / _` | '_ \\ / __/ _ \\      \n");
        printf(" | |  | | |_| | | |_| | |_) | (_| | | | | (_| (_) |         \n");
        printf(" |_|  |_|\\__,_|_|\\__|_|_.__/ \\__,_|_| |_|\\___\\___/     \n");
        printf("=======================================================     \n");
        if (!first && !blocked_account)
        {
            getchar();
        }
        else
        {
            first = false;
        }

        if (current == 0)
        {
            if (!blocked_account)
            {
                for (i = 0; i < 3; i++)
                {
                    if (language == PORTUGUESE)
                    {
                        printf("Número de conta\n> ");
                    }
                    else if (language == ENGLISH)
                    {
                        printf("Account number\n> ");
                    }
                    fgets(account_entry, 10, stdin);
                    // if (isdigit(account_entry[3]))
                    // {
                    //     getchar();
                    // }

                    printf("PIN\n> ");
                    fgets(pin_entry, 10, stdin);
                    // if (isdigit(pin_entry[3]))
                    // {
                    //     getchar();
                    // }

                    if ((strcmp(pin_entry, PIN) == 0) && (strcmp(account_entry, ACCOUNT) == 0))
                    {
                        return MAIN;
                    }

                    if (language == PORTUGUESE)
                    {
                        printf("O utilizador não existe ou o PIN está errado!\n\n");
                    }
                    else if (language == ENGLISH)
                    {
                        printf("Username does not exist or PIN is incorrect!\n\n");
                    }
                }

                if (language == PORTUGUESE)
                {
                    printf("3 tentativas erradas!\nA sua conta foi bloqueada.\n\n");
                }
                else if (language == ENGLISH)
                {
                    printf("3 failed trys!\nYour account is blocked.\n\n");
                }
                blocked_account = true;
                system("pause");
                current = 1;
            }
            else
            {
                if (language == PORTUGUESE)
                {
                    printf("A conta está bloqueada!\n");
                }
                else if (language == ENGLISH)
                {
                    printf("Your account is blocked!\n");
                }
                system("pause");
                current = 1;
            }
        }
        // menu inicial
        else if (current == 1)
        {
            int selected;
            if (language == PORTUGUESE)
            {
                printf("1 Login\n2 Linguagem\n3 Sair\n> ");
            }
            else if (language == ENGLISH)
            {
                printf("1 Login\n2 Language\n3 Exit\n> ");
            }

            scanf("%d", &selected);

            if (selected == 1)
            {
                current = 0;
            }
            else if (selected == 2)
            {
                current = 2;
            }
            else if (selected == 3)
            {
                running = false;
                system("cls");
                return -1;
            }
        }
        else if (current == 2)
        {
            int option;

            if (language == PORTUGUESE)
            {
                printf("1 Português (atual)\n2 Inglês\n3 Cancelar\n\n> ");
            }
            else if (language == ENGLISH)
            {
                printf("1 Portuguese\n2 English (current)\n3 Cancel\n\n> ");
            }
            scanf("%d", &option);

            if (option == 1)
            {
                language = PORTUGUESE;
                current = 1;
            }
            else if (option == 2)
            {
                language = ENGLISH;
                current = 1;
            }
            else if (option == 3)
            {
                current = 1;
            }
        }
    }
}

int main_menu()
{
    while (true)
    {
        system("cls");
        printf("  __  __                                \n");
        printf(" |  \\/  |                              \n");
        printf(" | \\  / |   ___   _ __    _   _        \n");
        printf(" | |\\/| |  / _ \\ | '_ \\  | | | |     \n");
        printf(" | |  | | |  __/ | | | | | |_| |        \n");
        printf(" |_|  |_|  \\___| |_| |_|  \\__,_|      \n");
        printf("====================================    \n");
        if (language == PORTUGUESE)
        {
            printf("1 Alteração de PIN\n2 Levantamentos\n3 Depósitos\n4 Consultar Saldo\n5 Sair\n\n> ");
        }
        else if (language == ENGLISH)
        {
            printf("1 Change PIN\n2 Withdraw\n3 Deposit\n4 Balance\n5 Exit\n\n> ");
        }

        int select_option;
        scanf("%d", &select_option);

        if (select_option < 6 && select_option > 0)
        {
            return (select_option + 1);
        }
    }
}

int change_pin_menu()
{
    bool firstTime = true;
    while (true)
    {
        system("cls");

        char new_pin[10] = "";

        if (language == PORTUGUESE)
        {
            printf("Novo PIN: ");
        }
        else if (language == ENGLISH)
        {
            printf("New PIN: ");
        }
        if (firstTime)
        {
            getchar();
        }
        fgets(new_pin, 10, stdin);
        printf("%s", new_pin);

        bool only_numbers = true;
        int i;
        int len = strlen(new_pin) - 1;
        int estimatedLen = 5;
        // if (firstTime)
        // {
        //     //len = strlen(new_pin) - 1;
        //     //estimatedLen = 5;
        // }
        // else
        // {
        //     //len = strlen(new_pin);
        //     //estimatedLen = 4;
        // }
        for (i = 0; i < len; i++)
        {
            if (!isdigit(new_pin[i]))
            {
                //printf("-->%c<--", new_pin[i]);
                only_numbers = false;
            }
        }

        //printf("--->%d<-------->%d<----", strlen(new_pin), only_numbers);

        if ((strlen(new_pin) != estimatedLen) || (!only_numbers))
        {
            if (language == PORTUGUESE)
            {
                printf("O PIN deve ser composto por 4 digitos númericos.\n");
            }
            else if (language == ENGLISH)
            {
                printf("PIN should be 4 numeric digits long.\n");
            }

            system("pause");
            firstTime = false;
            continue;
        }

        *PIN = malloc(256);
        strcpy(PIN, new_pin);

        if (language == PORTUGUESE)
        {
            printf("PIN alterado com sucesso!\n");
        }
        else if (language == ENGLISH)
        {
            printf("PIN changed successfully!\n");
        }

        // debug
        // printf("%s", PIN);
        system("pause");

        return MAIN;
    }
}

int withdraw_menu()
{
    system("cls");

    last_withdraw = true;

    success_withdraw = false;

    int amount, option;

    while (true)
    {
        if (language == PORTUGUESE)
        {
            printf("1 20\n2 40\n3 60\n4 100\n5 200\n6 Outras Importâncias\n\n> ");
        }
        else if (language == ENGLISH)
        {
            printf("1 20\n2 40\n3 60\n4 100\n5 200\n6 Other Amount\n\n> ");
        }
        scanf("%d", &option);

        switch (option)
        {
        case 1:
            amount = 20;
            break;
        case 2:
            amount = 40;
            break;
        case 3:
            amount = 60;
            break;
        case 4:
            amount = 100;
            break;
        case 5:
            amount = 200;
            break;
        case 6:
            system("cls");
            if (language == PORTUGUESE)
            {
                printf("Montante a levantar\n> ");
            }
            else if (language == ENGLISH)
            {
                printf("Amount to withdraw\n> ");
            }
            char amount_buf[200];
            scanf("%s", amount_buf);

            int x;
            for (x = 0; x < strlen(amount_buf); x++)
            {
                if (!isdigit(amount_buf[x]))
                {
                    system("cls");
                    if (language == PORTUGUESE)
                    {
                        printf("Valor inválido.\n\n");
                    }
                    else if (language == ENGLISH)
                    {
                        printf("Invalid value.\n\n");
                    }
                    return CONTINUE;
                }
            }

            amount = atoi(amount_buf);
            break;
        default:
            system("cls");
            continue;
        }
        amount_withdraw = amount;
        return withdraw_calculate();
    }
}

int withdraw_calculate()
{
    int fifthy_bill = 0, twenty_bill = 0, ten_bill = 0;

    system("cls");
    if (amount_withdraw < 10)
    {
        if (language == PORTUGUESE)
        {
            printf("Levantamentos de valores abaixo de 10 euros não são aceites.\n\n");
        }
        else if (language == ENGLISH)
        {
        }
        printf("Withdrawals below 10 euros are not accepted.\n\n");
    }
    else if (amount_withdraw > 400)
    {
        if (language == PORTUGUESE)
        {
            printf("Levantamentos de valores acima de 400 euros não são aceites.\n\n");
        }
        else if (language == ENGLISH)
        {
            printf("Withdrawals above 400 euros are not accepted.\n\n");
        }
    }
    else
    {
        if (amount_withdraw % 10 != 0)
        {
            if (language == PORTUGUESE)
            {
                printf("Apenas levantamentos valores múltiplos de 10 são aceites.\n\n");
            }
            else if (language == ENGLISH)
            {
                printf("Only multiple numbers of 10 are accepted for withdrawals.\n\n");
            }
        }
        else if (balance - amount_withdraw < 0)
        {
            if (language == PORTUGUESE)
            {
                printf("Dinheiro insuficiente.\n\n");
            }
            else if (language == ENGLISH)
            {
                printf("Insufficient balance.\n\n");
            }
        }
        else
        {
            fifthy_bill = amount_withdraw / 50;

            if (amount_withdraw % 50 != 0)
            {
                twenty_bill = (amount_withdraw % 50) / 20;

                if ((amount_withdraw % 50) % 20 != 0)
                {
                    ten_bill = ((amount_withdraw % 50) % 20) / 10;
                }
            }

            success_withdraw = true;

            if (language == PORTUGUESE)
            {
                printf("Notas a retirar:\n\n50 euros: %d\n20 euros: %d\n10 euros: %d\n\n", fifthy_bill, twenty_bill, ten_bill);
            }
            else if (language == ENGLISH)
            {
                printf("Bills to withdraw:\n\n50 euros: %d\n20 euros: %d\n10 euros: %d\n\n", fifthy_bill, twenty_bill, ten_bill);
            }
        }
    }

    return CONTINUE;
}

int deposit_menu()
{
    system("cls");

    last_deposit = true;

    success_deposit = false;

    if (language == PORTUGUESE)
    {
        printf("Valor a depositar\n> ");
    }
    else if (language == ENGLISH)
    {
        printf("Amount to deposit\n> ");
    }
    char amount_buf[200];
    scanf("%s", amount_buf);

    int x;
    for (x = 0; x < strlen(amount_buf); x++)
    {
        if (!isdigit(amount_buf[x]))
        {
            system("cls");
            if (language == PORTUGUESE)
            {
                printf("Montante inválido para depósito.\n\n");
            }
            else if (language == ENGLISH)
            {
                printf("Invalid deposit amount.\n\n");
            }

            return CONTINUE;
        }
    }

    amount_deposit = atoi(amount_buf);

    if (amount_deposit <= 0)
    {
        system("cls");
        if (language == PORTUGUESE)
        {
            printf("Montante inválido para depósito.\n\n");
        }
        else if (language == ENGLISH)
        {
            printf("Invalid deposit amount.\n\n");
        }
    }
    else
    {
        printf("\n");
        success_deposit = true;
    }

    return CONTINUE;
}

int balance_menu()
{
    system("cls");
    if (language == PORTUGUESE)
    {
        printf("Saldo atual: %d\n\n", balance);
    }
    else if (language == ENGLISH)
    {
        printf("Current balance: %d\n\n", balance);
    }
    system("pause");

    return MAIN;
}

int continue_menu()
{
    if (last_withdraw)
    {
        last_withdraw = false;

        char answer;

        if (success_withdraw)
        {
            while (true)
            {
                if (language == PORTUGUESE)
                {
                    printf("Deseja mesmo continuar? (s/n)\n> ");
                    scanf(" %c", &answer);
                    printf("\n");

                    if (answer == 's' || answer == 'S')
                    {
                        // remove money from account
                        balance -= amount_withdraw;
                        return MAIN;
                    }
                    else if (answer == 'n' || answer == 'N')
                    {
                        return MAIN;
                    }
                }
                else if (language == ENGLISH)
                {
                    printf("Do you really want to continue? (y/n)\n> ");
                    scanf(" %c", &answer);
                    printf("\n");

                    if (answer == 'y' || answer == 'Y')
                    {
                        // remove money from account
                        balance -= amount_withdraw;
                        return MAIN;
                    }
                    else if (answer == 'n' || answer == 'N')
                    {
                        return MAIN;
                    }
                }
            }
        }
        else
        {
            while (true)
            {
                if (language == PORTUGUESE)
                {
                    printf("Deseja voltar a tentar? (s/n)\n> ");
                    scanf(" %c", &answer);
                    printf("\n");

                    if (answer == 's' || answer == 'S')
                    {
                        return WITHDRAW;
                    }
                    else if (answer == 'n' || answer == 'N')
                    {
                        return MAIN;
                    }
                }
                else if (language == ENGLISH)
                {
                    printf("Do you want to try again? (y/n)\n> ");
                    scanf(" %c", &answer);
                    printf("\n");

                    if (answer == 'y' || answer == 'Y')
                    {
                        return WITHDRAW;
                    }
                    else if (answer == 'n' || answer == 'N')
                    {
                        return MAIN;
                    }
                }
            }
        }
    }
    else if (last_deposit)
    {
        last_deposit = false;

        char answer;

        if (success_deposit)
        {
            while (true)
            {
                if (language == PORTUGUESE)
                {
                    printf("Deseja mesmo continuar? (s/n)\n> ");
                    scanf(" %c", &answer);
                    printf("\n");

                    if (answer == 's' || answer == 'S')
                    {
                        // add money from account
                        balance += amount_deposit;

                        return MAIN;
                    }
                    else if (answer == 'n' || answer == 'N')
                    {
                        return MAIN;
                    }
                }
                else if (language == ENGLISH)
                {
                    printf("Do you really want to continue? (y/n)\n> ");
                    scanf(" %c", &answer);
                    printf("\n");

                    if (answer == 'y' || answer == 'Y')
                    {
                        // add money from account
                        balance += amount_deposit;

                        return MAIN;
                    }
                    else if (answer == 'n' || answer == 'N')
                    {
                        return MAIN;
                    }
                }
            }
        }
        else
        {
            while (true)
            {
                if (language == PORTUGUESE)
                {
                    printf("Deseja voltar a tentar? (s/n)\n> ");
                    scanf(" %c", &answer);
                    printf("\n");

                    if (answer == 's' || answer == 'S')
                    {
                        return DEPOSIT;
                    }
                    else if (answer == 'n' || answer == 'N')
                    {
                        return MAIN;
                    }
                }
                else if (language == ENGLISH)
                {
                    printf("Do you want to try again? (y/n)\n> ");
                    scanf(" %c", &answer);
                    printf("\n");

                    if (answer == 'y' || answer == 'Y')
                    {
                        return DEPOSIT;
                    }
                    else if (answer == 'n' || answer == 'N')
                    {
                        return MAIN;
                    }
                }
            }
        }
    }
    else if (current_menu == EXIT)
    {
        char answer;
        if (language == PORTUGUESE)
        {
            printf("Deseja mesmo sair? (s/n)\n> ");
            scanf(" %c", &answer);
            printf("\n");

            if (answer == 's' || answer == 'S')
            {
                return LOGIN;
            }
            else if (answer == 'n' || answer == 'N')
            {
                return MAIN;
            }
        }
        else if (language == ENGLISH)
        {
            printf("Do you really want to leave? (y/n)\n> ");
            scanf(" %c", &answer);
            printf("\n");

            if (answer == 'y' || answer == 'Y')
            {
                return LOGIN;
            }
            else if (answer == 'n' || answer == 'N')
            {
                return MAIN;
            }
        }
    }
}
