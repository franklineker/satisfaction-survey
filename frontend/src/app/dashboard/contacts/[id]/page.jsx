import fetchContact from "@/api/contact/fetchContact";
import CrudButton from "@/components/ui/buttons/CrudButton";


export default async function ContactDetail({ params }) {

    const { id } = await params;
    const contact = await fetchContact(id);

    const { fullName, email, phoneNumber, taxNumber } = contact;

    return (
        <div className="flex flex-col">
            <CrudButton
                name="Editar"
                type="edit"
                className="self-end mb-2"
                contactid={id} />
            <div className="bg-white shadow-md rounded-xl p-6 w-[70%] mx-auto">
                <h3 className="text-xl font-semibold mb-2">{fullName}</h3>
                <p className="text-gray-700"><span className="font-medium">Email:</span> {email}</p>
                {phoneNumber && (
                    <p className="text-gray-700"><span className="font-medium">Telefone:</span> {phoneNumber}</p>
                )}
                {taxNumber && (
                    <p className="text-gray-700"><span className="font-medium">CPF/CNPJ:</span> {taxNumber}</p>
                )}
            </div>
        </div>
    );
}
