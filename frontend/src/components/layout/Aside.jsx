import Link from "next/link";

export default function Aside() {
    return (
        <aside className="text-white">
            <nav className="h-full">
                <ul className="flex flex-col h-full">
                    <li className="flex m-1 hover:bg-amber-400 hover:text-black rounded-sm" >
                        <Link className="h-full w-full p-1" href="/"><b>Início</b></Link>
                    </li>
                    <li className="flex m-1 hover:bg-amber-400 hover:text-black rounded-sm" >
                        <Link className="h-full w-full p-1" href="/surveys"><b>Pesquisas</b></Link>
                    </li>
                    <li className="flex m-1 hover:bg-amber-400 hover:text-black rounded-sm" >
                        <Link className="h-full w-full p-1" href="/campaigns"><b>Campanhas</b></Link>
                    </li>
                    <li className="flex m-1 hover:bg-amber-400 hover:text-black rounded-sm" >
                        <Link className="h-full w-full p-1" href="/contacts"><b>Contatos</b></Link>
                    </li>
                </ul>
            </nav>
        </aside>
    )
}
