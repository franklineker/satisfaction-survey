"use client";

import {
    generateCodeChallenge,
    generateCodeVerifier,
    getAuthorizationUrl,
} from "@/utils/auth";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function SignIn() {
    const [authorizationUrl, setAuthorizationUrl] = useState("");
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const setupPKCE = async () => {
            try {
                const codeVerifier = generateCodeVerifier();

                sessionStorage.setItem("code_verifier", codeVerifier);

                const codeChallenge = await generateCodeChallenge(codeVerifier);
                const url = getAuthorizationUrl(codeChallenge);
                setAuthorizationUrl(url);
            } catch (error) {
                console.error("Failed to generate PKCE parameters:", error);
            } finally {
                setLoading(false);
            }
        };

        setupPKCE();
    }, []);

    if (loading) {
        return <div>Gerando URL de login...</div>;
    }

    return (
        <div>
            <h1>Entrar no Dr. Navalha</h1>
            <p>Você precisa estar logado para acessar.</p>
            <Link href={authorizationUrl}>
                <button className="hover:cursor-pointer">
                    Login com o Authorization Server
                </button>
            </Link>
        </div>
    );
}
