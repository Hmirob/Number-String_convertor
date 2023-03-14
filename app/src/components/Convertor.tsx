import React, {useState} from "react";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {useNavigate} from "react-router-dom";
import {Alert, AlertColor} from "@mui/material";

const theme = createTheme();

export function Convertor() {
    const navigate = useNavigate();

    const [numberToConversation, setNumberToConversation] = useState<string>("0");
    const [stringToConversation, setStringToConversation] = useState<string>("ноль");

    const [alertColor, setAlertColor] = useState<AlertColor>("info");
    const [alertText, setAlertText] = useState<String>("Тыкай куда нибудь и эта штука изменится");

    function convert(conversationType: "NumberToString" | "StringToNumber") {
        let url: string = `/convert?type=${conversationType}&value=${
            conversationType === "NumberToString" ? numberToConversation : stringToConversation
        }`;
        fetch(url).then((response) => {
            switch (response.status) {
                case 200:
                    setAlertColor("success");
                    setAlertText("Отлично! Продолжай в том же духе и постарайся ничего не сломать");
                    break;
                case 400:
                    setAlertColor("warning");
                    setAlertText("Похоже ты что-то не так ввёл, очепятка??");
                    throw new Error("beda");
                case 500:
                    setAlertColor("error");
                    setAlertText("Ты всё сломал! Сервер ответил 500");
                    throw new Error("beda");
            }
            return response.text();
        }).then((str) => {
            if (conversationType === "NumberToString") {
                setStringToConversation(str);
            } else {
                setNumberToConversation(str);
            }
        }).catch((e) => {
            console.error(e);
        });
    }

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="lg">
                <CssBaseline/>
                <Box
                    sx={{
                        marginTop: 8,
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                    }}
                >
                    <TextField
                        margin="normal"
                        type="number"
                        fullWidth
                        id="number"
                        label="Число"
                        name="number"
                        autoFocus
                        value={numberToConversation}
                        onChange={(e) => setNumberToConversation(e.target.value)}
                    />
                    <TextField
                        margin="normal"
                        fullWidth
                        id="string"
                        label="Строка"
                        name="string"
                        autoFocus
                        value={stringToConversation}
                        onChange={(e) => setStringToConversation(e.target.value)}
                    />
                    <Alert severity={alertColor}>{alertText}</Alert>
                    <Button
                        type="button"
                        fullWidth
                        variant="contained"
                        sx={{mt: 3, mb: 1}}
                        onClick={() => convert("NumberToString")}
                    >
                        Конвертировать число в строку
                    </Button>
                    <Button
                        type="button"
                        fullWidth
                        variant="contained"
                        sx={{mt: 1, mb: 1}}
                        onClick={() => convert("StringToNumber")}
                    >
                        Конвертировать строку в число
                    </Button>
                    <Button
                        type="button"
                        fullWidth
                        color="error"
                        variant="contained"
                        sx={{mt: 3, mb: 1}}
                        onClick={() => fetch("/logout").then((response: Response) => {
                            navigate(response.url.split("/").pop()!);
                        })}
                    >
                        Выйти
                    </Button>
                </Box>
            </Container>
        </ThemeProvider>
    );
}