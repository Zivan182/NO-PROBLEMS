

export async function requestToServer(
    method: string,
    url: string,
    body: any = undefined,
    addToken: boolean = true
) 
{
    // if (addToken && !isLoggedIn()) {
    //     redirect("/");
    // }
    console.log(window.localStorage.getItem("jwtToken"));

    const TokenToSend = window.localStorage.getItem("jwtToken") != null ? window.localStorage.getItem("jwtToken") : "";

    console.log(TokenToSend);
    return fetch(url, {
        method: method,
        body: body,
        headers: (
            addToken ? {
                'Content-Type': 'application/json; charset=UTF-8',
                'ApiToken': TokenToSend!
            } : { 'Content-Type': 'application/json; charset=UTF-8' }
        )
    });
}


export async function logOut() {
    requestToServer(
        "GET",
        "/users/logout"
    ).then((v) => {
        window.localStorage.removeItem("jwtToken");
        window.location.href = "/";
    });
}

export function isLoggedIn(): boolean {
    return window.localStorage.getItem("jwtToken") != null;
}