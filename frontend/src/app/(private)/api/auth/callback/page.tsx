"use client";

import { useSearchParams, useRouter } from "next/navigation";
import { useEffect } from "react";

export default function Callback() {
    const searchParams = useSearchParams();
    const router = useRouter();
    const code = searchParams.get("code");

    useEffect(() => {
        const exchangeToken = async () => {
            if (!code) return;

            const codeVerifier = sessionStorage.getItem("code_verifier");

            if (!codeVerifier) {
                console.error("Code verifier not found in session storage.");
                router.replace("/auth/login?error=true");
                return;
            }

            sessionStorage.removeItem("code_verifier");

            try {
                const response = await fetch("/auth/api/token", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ code, codeVerifier }),
                });

                if (!response.ok) {
                    throw new Error("Fail to exchange tokens.");
                }

                const tokens = await response.json();
                console.log("Tokens successfully received: ", tokens);

                router.replace("/");
            } catch (error) {
                console.error(error);
                router.replace("/auth/login?error=true");
            }
        };

        exchangeToken();
    }, [code, router]);

    return (
        <div className="flex items-center justify-center h-screen">
            <h1 className="text-xl font-semibold">Carregando...</h1>
        </div>
    );
}
